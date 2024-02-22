package co.istad.elearning.api.role;


import java.util.List;

public interface RoleService {
    public List<RoleResDto> getAllRoles();
    public RoleResDto getRoleByID(Integer id);
}
