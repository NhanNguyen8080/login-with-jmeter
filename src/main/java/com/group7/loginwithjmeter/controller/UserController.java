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
                new ResponseObject(userService.getAllUsers())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestParam("username") String username,
                                                @RequestParam("password") String password) {
        if (username.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Username is empty!")
            );
        }
        if (password.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Password is empty!")
            );
        }
        if (username.trim().isEmpty() && password.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Username and password is empty!")
            );
        }
        if (!userService.getUserByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Account does not exist!")
            );
        }
        if (!userService.login(username, password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Invalid password!")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Login successfully")
        );
    }
}
