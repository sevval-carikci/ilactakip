package com.ilactakip.services;

import com.ilactakip.dao.Dao; // Eğer Role işlemlerini Dao üzerinden yapacaksan
import com.ilactakip.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleService {

    private final Dao dao; // Role işlemleri Dao üzerinden yapılacak

    public RoleService(Dao dao) { // Parantez hatasını düzelttim
        this.dao = dao;
    }

    /* ================== GET ALL ================== */
    public List<Role> getAllRoles() {
        return dao.findAllRoles();
    }

    /* ================== GET BY ID ================== */
    public Role getRoleById(Long id) {
        Role role = dao.findRoleById(id);
        if (role == null) {
            throw new RuntimeException("Role bulunamadı. ID: " + id);
        }
        return role;
    }

    /* ================== CREATE ================== */
    public Role createRole(Role role) {
        return dao.saveRole(role);
    }

    /* ================== UPDATE ================== */
    public Role updateRole(Long id, Role role) {
        Role existing = getRoleById(id);
        existing.setName(role.getName());
        return dao.updateRole(existing);
    }

    /* ================== PARTIAL UPDATE ================== */
    public Role partialUpdateRole(Long id, Role role) {
        Role existing = getRoleById(id);
        if (role.getName() != null) existing.setName(role.getName());
        return dao.updateRole(existing);
    }

    /* ================== DELETE ================== */
    public void deleteRole(Long id) {
        Role existing = getRoleById(id);
        dao.deleteRole(existing);
    }
}

