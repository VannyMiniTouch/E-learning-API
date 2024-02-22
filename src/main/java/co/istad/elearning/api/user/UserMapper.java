package co.istad.elearning.api.user;

import co.istad.elearning.api.auth.RegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateDto(UserCreateDto userCreateDto);


    UserCreateDto mapRegisterDtoToUserCreationDto(RegisterDto registerDto);
}
