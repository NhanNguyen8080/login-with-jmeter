package com.group7.loginwithjmeter.controller;

import com.group7.loginwithjmeter.model.ResponseObject;
import com.group7.loginwithjmeter.model.User;
import com.group7.loginwithjmeter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return ResponseEntity.ok(
                new ResponseObject("OK", "Query successfully", userService.getAllUsers())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody User user) {
        if (user.getUsername().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("FAILED", "Username is empty!", null)
            );
        }
        if (user.getPassword().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("FAILED", "Password is empty!", null)
            );
        }
        if (user.getUsername().trim().isEmpty() && user.getPassword().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("FAILED", "Username and password is empty!", null)
            );
        }
        if (userService.getUserByUsername(user.getUsername()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("FAILED", "Account does not exist!", null)
            );
        }
        if (!userService.login(user.getUsername(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("FAILED", "Invalid password", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Login successfully", new String("Welcome " + user.getUsername()))
        );
    }
}
