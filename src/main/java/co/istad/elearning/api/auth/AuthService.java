package co.istad.elearning.api.auth;

import co.istad.elearning.api.auth.dtos.*;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface AuthService {
    AuthDto refresh(RefreshTokenDto refreshTokenDto);

    Map<String, Object> register(RegisterDto registerDto) throws MessagingException;

    Map<String, Object> verify(VerifyDto verifyDto);

    AuthDto login(LoginDto loginDto);
}
