package example.service;


import example.dao.RoleDAO;
import example.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> allRoles() {
        return roleDAO.allRoles();
    }

    @Override
    public Role findByRoleName(String role) {
        return roleDAO.findByRoleName(role);
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleNames) {
        return  roleDAO.getSetOfRoles(roleNames);
    }

}
