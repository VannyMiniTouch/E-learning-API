package co.istad.elearning.api.role;

import co.istad.elearning.api.auth.AuthorityResDto;

import java.util.List;

public record RoleResDto(
        String name,
//        List<UserResDto> users,
        List<AuthorityResDto> authorities

) {
}
