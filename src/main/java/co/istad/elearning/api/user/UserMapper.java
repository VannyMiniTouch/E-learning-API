package co.istad.elearning.api.user;

import co.istad.elearning.api.auth.dtos.RegisterDto;
import co.istad.elearning.api.user.dto.UserCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateDto(UserCreateDto userCreateDto);


    UserCreateDto mapRegisterDtoToUserCreationDto(RegisterDto registerDto);
}
