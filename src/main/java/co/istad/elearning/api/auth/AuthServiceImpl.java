package co.istad.elearning.api.auth;

//import co.istad.elearningapi.dto.RegisterDto;
//import co.istad.elearningapi.dto.UserCreateDto;
//import co.istad.elearningapi.dto.VerifyDto;
//import co.istad.elearningapi.entity.User;
//import co.istad.elearningapi.mapper.UserMapper;
//import co.istad.elearningapi.repository.AuthRepository;
//import co.istad.elearningapi.service.AuthService;
//import co.istad.elearningapi.service.UserService;
//import co.istad.elearningapi.utility.RandomUtil;


import co.istad.elearning.api.auth.dtos.AuthDto;
import co.istad.elearning.api.auth.dtos.LoginDto;
import co.istad.elearning.api.user.User;
import co.istad.elearning.api.user.UserCreateDto;
import co.istad.elearning.api.user.UserMapper;
import co.istad.elearning.api.user.UserService;
import co.istad.elearning.api.utility.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthRepository authRepository;
    private  final JavaMailSender javaMailSender;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;

    @Value("username")
    private String adminMail;

    @Transactional

    @Override
    public Map<String, Object> register(RegisterDto registerDto) throws MessagingException {
        if(!registerDto.password().equals(registerDto.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password doesn't match");
        }
        UserCreateDto userCreateDto = userMapper.mapRegisterDtoToUserCreationDto(registerDto);
        userService.createNew(userCreateDto);

        //update verified code into db
        String sixDigits = RandomUtil.random6Digits();
        authRepository.updateVerifiedCode(registerDto.email(), sixDigits);

        //Send mail with verified code
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("Account Verification");
        mimeMessageHelper.setText(sixDigits);
        mimeMessageHelper.setTo(registerDto.email());
        mimeMessageHelper.setFrom(adminMail);
        javaMailSender.send(mimeMessage);

        return Map.of("message" ,"Please verify Email" , "email", registerDto.email());
    }

    @Override
    public Map<String, Object> verify(VerifyDto verifyDto) {
        User user = authRepository.findByEmailAndVerifiedCode(
                verifyDto.email(),
                verifyDto.verifiedCode()
        )
                .orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User has not been found")
        );
        user.setIsVerified(true);
        user.setVerifiedCode(null);
        authRepository.save(user);
        return Map.of(
                "message", " your account has been verified",
                "email", verifyDto.email()

        );
    }

    private AuthDto createAccessToken(Authentication authentication){
        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(authentication.getName())
                .audience(List.of("Mobile", "Web"))
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .issuer(authentication.getName())
                .subject("Access Token")
                .claim("scope", scope)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return AuthDto.builder()
                .tokendType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    @Override
    public AuthDto login(LoginDto loginDto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginDto.email(),
                loginDto.password()
        );
        auth = daoAuthenticationProvider.authenticate(auth);

        log.info("Auth: {}", auth);
        log.info("Auth: {}", auth.getName());
        log.info("Auth: {}", auth.getAuthorities());

        return this.createAccessToken(auth);
    }
}
