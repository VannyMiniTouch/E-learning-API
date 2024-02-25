package co.istad.elearning.api.role;

import co.istad.elearning.api.role.dto.RoleResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RoleMapper {

    @Mapping(target = "authorities.name", source = "authorities.name")
    List<RoleResDto> toRolesResDto(List<Role> role);
    RoleResDto toRoleResDto(Role role);
}
