package co.istad.elearning.api.auth;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface AuthService {
    Map<String, Object> register(RegisterDto registerDto) throws MessagingException;

    Map<String, Object> verify(VerifyDto verifyDto);
}
