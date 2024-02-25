package co.istad.elearning.api.JpaSpecification;

import lombok.Builder;

@Builder
public record PersonDto(
         Long id,
         String name,
         String email,
         String password,
         String countryCode
) {
}
