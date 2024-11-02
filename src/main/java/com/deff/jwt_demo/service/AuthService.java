package com.deff.jwt_demo.service;

import com.deff.jwt_demo.model.Usuario;

public interface AuthService {

    String registerUser(Usuario usuario);
    String authenticateUser(Usuario usuario);

}
