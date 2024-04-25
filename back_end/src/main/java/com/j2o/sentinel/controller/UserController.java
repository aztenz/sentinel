package com.j2o.sentinel.controller;

import com.j2o.sentinel.model.User;
import com.j2o.sentinel.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userServ;

    //To get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServ.getAllUsers();
        ResponseEntity.ok(users);
    }

    //To get users by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userServ.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //To create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userServ.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    //To update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userServ.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    //To delete an existing user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServ.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
