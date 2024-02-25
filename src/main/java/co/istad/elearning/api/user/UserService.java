package co.istad.elearning.api.user;


import co.istad.elearning.api.user.dto.UserCreateDto;

public interface UserService {
    void createNew(UserCreateDto userCreateDto);
}
