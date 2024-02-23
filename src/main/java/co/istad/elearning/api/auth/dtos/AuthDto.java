package co.istad.elearning.api.auth.dtos;

import lombok.Builder;

@Builder
public record AuthDto(

       String tokendType,
       String accessToken,
      String  refreshToken

) {
}
