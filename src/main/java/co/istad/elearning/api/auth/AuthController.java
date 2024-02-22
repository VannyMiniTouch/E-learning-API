package co.istad.elearning.api.auth;

import co.istad.elearning.api.auth.dtos.LoginDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtEncoder jwtEncoder;

    @PostMapping("/login")
    Map<String, Object> login(@Valid @RequestBody LoginDto loginDto) {
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(loginDto.email())
                .audience(List.of("Mobile", "Web"))
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject("Access Token")
                .build();

        String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return Map.of("token", jwtToken);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    Map<String, Object> register(@Valid @RequestBody RegisterDto registerDto) throws MessagingException {

        return authService.register(registerDto);
    }

    @PostMapping("/verify")
    Map<String, Object> verify(@Valid @RequestBody VerifyDto verifyDto) {
        return authService.verify(verifyDto);
    }
}
