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


import co.istad.elearning.api.user.User;
import co.istad.elearning.api.user.UserCreateDto;
import co.istad.elearning.api.user.UserMapper;
import co.istad.elearning.api.user.UserService;
import co.istad.elearning.api.utility.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthRepository authRepository;
    private  final JavaMailSender javaMailSender;

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
}
