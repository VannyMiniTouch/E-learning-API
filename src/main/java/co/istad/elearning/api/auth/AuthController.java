package co.istad.elearning.api.auth;

import co.istad.elearning.api.auth.dtos.AuthDto;
import co.istad.elearning.api.auth.dtos.LoginDto;
import co.istad.elearning.api.auth.dtos.RegisterDto;
import co.istad.elearning.api.auth.dtos.VerifyDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
//    private final JwtEncoder jwtEncoder;

    @PostMapping("/login")
    AuthDto login(@RequestBody LoginDto loginDto){
      return   authService.login(loginDto);
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
