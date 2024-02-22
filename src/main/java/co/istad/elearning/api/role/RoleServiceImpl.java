package co.istad.elearning.api.role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
private final RoleRepository roleRepository;
private final RoleMapper roleMapper;
    @Override
    public List<RoleResDto> getAllRoles() {
       List<Role> role = roleRepository.findAll();
        return roleMapper.toRolesResDto(role);
    }

    @Override
    public RoleResDto getRoleByID(Integer id) {
      Role role = roleRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Role not found"
               ));
    return roleMapper.toRoleResDto(role);
    }

}
