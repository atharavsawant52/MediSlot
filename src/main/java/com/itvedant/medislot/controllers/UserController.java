
package com.itvedant.medislot.controllers;

import com.itvedant.medislot.models.User;
import com.itvedant.medislot.models.UserDto;
import com.itvedant.medislot.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {

        try {
            User user = userService.registerUser(userDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of(
                            "message", "User registered successfully",
                            "id", user.getId(),
                            "username", userDto.getUsername(),
                            "email", user.getEmail(),
                            "role", user.getRoles()
                    )
            );

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }
}
