package com.smart.parking.controller;

import com.smart.parking.dto.ChangePasswordRequest;
import com.smart.parking.dto.UserRequest;
import com.smart.parking.dto.UserRequestDto;
import com.smart.parking.entity.User;
import com.smart.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/me")
    public UserRequest getCurrentUser(@AuthenticationPrincipal User user) {
        return service.user(user);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<UserRequestDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        service.updateUser(id, userRequestDto);
        return ResponseEntity.ok(userRequestDto);
    }

    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping("/get/all")
    public List<User> getAllUsers() {
        return service.findAll();
    }
}
