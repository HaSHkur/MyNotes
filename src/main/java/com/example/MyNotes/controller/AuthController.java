package com.example.MyNotes.controller;

import com.example.MyNotes.domain.UserDomain;
import com.example.MyNotes.entity.User;
import com.example.MyNotes.security.JwtUtil;
import com.example.MyNotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register/{username}/{password}")
    public ResponseEntity<UserDomain> register(
            @PathVariable String name,
            @PathVariable String email,
            @PathVariable String username,
            @PathVariable String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        return ResponseEntity.ok(userService.register(user));
    }


    @PostMapping("/login/{username}/{password}")
    public Map<String, String> login(
            @PathVariable String username,
            @PathVariable String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = jwtUtil.generateToken(username);
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            return Map.of("error", "Invalid username or password");
        }
    }

}