package com.deff.jwt_demo.service;

import com.deff.jwt_demo.model.Usuario;
import com.deff.jwt_demo.repository.UsuarioRepository;
import com.deff.jwt_demo.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String registerUser(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente.";
    }

    @Override
    public String authenticateUser(Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(usuario.getUsername());
    }

}
