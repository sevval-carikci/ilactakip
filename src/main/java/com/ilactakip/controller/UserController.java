package com.ilactakip.controller;

import com.ilactakip.dto.UserDto;
import com.ilactakip.entity.User;
import com.ilactakip.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    // 🔐 ADMIN – tüm kullanıcıları getir (DTO ile)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserDto> dtos = users.stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getRoles().stream()
                                .findFirst()
                                .map(role -> role.getName())
                                .orElse(null)
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // 🔐 ADMIN – kullanıcıyı username ile getir (DTO ile)
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        UserDto dto = new UserDto(
                user.getUsername(),
                user.getRoles().stream()
                        .findFirst()
                        .map(role -> role.getName())
                        .orElse(null)
        );
        return ResponseEntity.ok(dto);
    }

    // 🌍 PUBLIC – kayıt
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
        User user = userService.register(dto);
        UserDto responseDto = new UserDto(
                user.getUsername(),
                user.getRoles().stream()
                        .findFirst()
                        .map(role -> role.getName())
                        .orElse(null)
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 🌍 PUBLIC – login (Spring Security auth kullanıyor)
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Giriş başarılı");
    }

    // 🔐 ADMIN – kullanıcı sil
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // 🔐 ADMIN – kullanıcıyı tamamen güncelle (DTO ile)
    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable String username,
            @RequestBody UserDto dto
    ) {
        User updated = userService.updateUser(username, dto);
        UserDto responseDto = new UserDto(
                updated.getUsername(),
                updated.getRoles().stream()
                        .findFirst()
                        .map(role -> role.getName())
                        .orElse(null)
        );
        return ResponseEntity.ok(responseDto);
    }

    // 🔐 ADMIN – kullanıcıyı kısmi güncelle (DTO ile)
    @PatchMapping("/{username}")
    public ResponseEntity<UserDto> partialUpdateUser(
            @PathVariable String username,
            @RequestBody UserDto dto
    ) {
        User updated = userService.partialUpdateUser(username, dto);
        UserDto responseDto = new UserDto(
                updated.getUsername(),
                updated.getRoles().stream()
                        .findFirst()
                        .map(role -> role.getName())
                        .orElse(null)
        );
        return ResponseEntity.ok(responseDto);
    }

}
