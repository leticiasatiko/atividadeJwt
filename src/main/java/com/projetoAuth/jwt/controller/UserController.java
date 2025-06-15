package com.projetoAuth.jwt.controller;

import com.projetoAuth.jwt.model.userModel;
import com.projetoAuth.jwt.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Este é um endpoint público, acessível a todos.");
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminOnlyEndpoint() {
        return ResponseEntity.ok("Bem-vindo, ADMIN! Você tem acesso total.");
    }

    @GetMapping("/user-specific")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userSpecificEndpoint() {
        return ResponseEntity.ok("Bem-vindo, USUÁRIO! Você tem acesso às funcionalidades de usuário.");
    }

    @GetMapping("/list-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<userModel>> listAllUsers() {
        List<userModel> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
