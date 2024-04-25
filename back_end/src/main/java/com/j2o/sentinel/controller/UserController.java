package com.j2o.sentinel.controller;

import com.j2o.sentinel.dto.response.user.UserDetails;
import com.j2o.sentinel.dto.response.user.UserListItem;
import com.j2o.sentinel.model.User;
import com.j2o.sentinel.service.UserService;

import com.j2o.sentinel.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDetails> getProfile() {
        int userId = Util.getCurrentUserId();
        return ResponseEntity.ok(userService.getItem(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserListItem>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
}
