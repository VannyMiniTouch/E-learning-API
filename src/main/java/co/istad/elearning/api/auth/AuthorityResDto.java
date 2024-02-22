package co.istad.elearning.api.auth;

import lombok.Builder;

@Builder
public record AuthorityResDto(
        String name
) {
}
