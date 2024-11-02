package com.deff.jwt_demo;

import com.deff.jwt_demo.model.Usuario;
import com.deff.jwt_demo.repository.UsuarioRepository;
import com.deff.jwt_demo.security.JwtUtil;
import com.deff.jwt_demo.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Configuración de prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("testUser");
        usuario.setPassword("password");

        when(usuarioRepository.findByUsername("testUser")).thenReturn(java.util.Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Llamada al método
        String result = authService.registerUser(usuario);

        // Verificaciones
        assertEquals("Usuario registrado exitosamente.", result);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testAuthenticateUser() {
        // Configuración de prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("testUser");
        usuario.setPassword("password");

        when(jwtUtil.generateToken("testUser")).thenReturn("jwtToken");

        // Llamada al método
        String token = authService.authenticateUser(usuario);

        // Verificaciones
        assertEquals("jwtToken", token);
        verify(jwtUtil, times(1)).generateToken("testUser");
    }

}
