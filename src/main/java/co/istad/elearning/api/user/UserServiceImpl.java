package co.istad.elearning.api.user;

import co.istad.elearning.api.role.Role;
import co.istad.elearning.api.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createNew(UserCreateDto userCreateDto) {
        User user = userMapper.fromUserCreateDto(userCreateDto);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already existing...");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already existing...");
        }
        Set<Role> roles = new HashSet<>();
        userCreateDto.roleNames().forEach(name -> {
            Role role = roleRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Role"));
            roles.add(role);
        });
        user.setRoles(roles);
        user.setIsDeleted(false);
        user.setIsVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
