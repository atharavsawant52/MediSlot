
package com.itvedant.medislot.services;

import com.itvedant.medislot.models.User;
import com.itvedant.medislot.models.UserDto;
import com.itvedant.medislot.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMobile(userDto.getMobile());

        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            user.setRoles(List.of("ROLE_USER"));
        } else {
            user.setRoles(userDto.getRoles());
        }

        return userRepository.save(user);
    }
}
