package co.istad.elearning.api.role.dto;

import co.istad.elearning.api.auth.dtos.AuthorityResDto;

import java.util.List;

public record RoleResDto(
        String name,
//        List<UserResDto> users,
        List<AuthorityResDto> authorities

) {
}
