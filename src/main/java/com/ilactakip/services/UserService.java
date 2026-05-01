package com.ilactakip.services;

import com.ilactakip.dao.Dao;
import com.ilactakip.dto.UserDto;
import com.ilactakip.entity.Role;
import com.ilactakip.entity.User;
import com.ilactakip.exception.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

    private final Dao dao;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(Dao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    /* ================== LOAD USER FOR SPRING SECURITY ================== */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("Kullanıcı bulunamadı: " + username);
        }

        Collection<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    /* ================== GET ALL USERS ================== */
    public List<User> getAllUsers() {
        return dao.findAllUsers();
    }

    /* ================== GET USER BY ID ================== */
    public User getUserById(Long id) {
        User user = dao.findUserById(id);
        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı. ID: " + id);
        }
        return user;
    }

    /* ================== GET USER BY USERNAME ================== */
    public User getUserByUsername(String username) {
        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı: " + username);
        }
        return user;
    }

    /* ================== REGISTER ================== */
    public User register(UserDto dto) {

        if (dao.findUserByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("Bu kullanıcı adı zaten kayıtlı!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());

        // password ZORUNLU
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new RuntimeException("Şifre boş olamaz!");
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // role ekleme
        if (dto.getRole() != null) {
            Role role = dao.findRoleByName(dto.getRole());
            if (role == null) {
                throw new RuntimeException("Role bulunamadı: " + dto.getRole());
            }
            user.addRole(role);
        }

        return dao.saveUser(user);
    }

    /* ================== DELETE ================== */
    public void deleteUser(String username) {
        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("Silinmek istenen kullanıcı bulunamadı!");
        }
        dao.deleteUser(user);
    }

    public User updateUser(String username, UserDto dto) {
        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı: " + username);
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRole() != null) {
            Role role = dao.findRoleByName(dto.getRole());
            if (role == null) {
                throw new RuntimeException("Role bulunamadı: " + dto.getRole());
            }
            user.getRoles().clear();
            user.addRole(role);
        }

        return dao.saveUser(user);
    }

    public User partialUpdateUser(String username, UserDto dto) {
        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı: " + username);
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRole() != null) {
            Role role = dao.findRoleByName(dto.getRole());
            if (role == null) {
                throw new RuntimeException("Role bulunamadı: " + dto.getRole());
            }
            user.addRole(role); // PATCH → ekleme
        }

        return dao.saveUser(user);
    }

}

