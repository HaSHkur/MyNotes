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

    @PostMapping("/register")
    public ResponseEntity<UserDomain> register(@RequestBody User user) {
        UserDomain response = userService.register(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.get("username"), loginRequest.get("password"))
            );
            String token = jwtUtil.generateToken(loginRequest.get("username"));
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            return Map.of("error", "Invalid username or password");
        }
    }
}