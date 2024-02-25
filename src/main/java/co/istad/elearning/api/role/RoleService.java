package co.istad.elearning.api.role;


import co.istad.elearning.api.role.dto.RoleResDto;

import java.util.List;

public interface RoleService {
    public List<RoleResDto> getAllRoles();
    public RoleResDto getRoleByID(Integer id);
}
