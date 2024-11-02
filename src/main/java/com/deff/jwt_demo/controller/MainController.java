package com.deff.jwt_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Has ingresado a un endpoint protegido. Solo accesible con un token JWT válido.";
    }

}
