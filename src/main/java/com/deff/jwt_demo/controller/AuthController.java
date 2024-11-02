package com.deff.jwt_demo.controller;

import com.deff.jwt_demo.model.Usuario;
import com.deff.jwt_demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        try {
            String response = authService.registerUser(usuario);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody Usuario usuario) {
        try {
            String jwt = authService.authenticateUser(usuario);
            return ResponseEntity.ok(jwt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
