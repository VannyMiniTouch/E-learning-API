package co.istad.elearning.api.auth;

import co.istad.elearning.api.auth.dtos.AuthDto;
import co.istad.elearning.api.auth.dtos.LoginDto;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface AuthService {
    Map<String, Object> register(RegisterDto registerDto) throws MessagingException;

    Map<String, Object> verify(VerifyDto verifyDto);

    AuthDto login(LoginDto loginDto);
}
