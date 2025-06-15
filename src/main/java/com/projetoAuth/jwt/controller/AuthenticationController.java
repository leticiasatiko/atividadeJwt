package com.projetoAuth.jwt.controller;

import com.projetoAuth.jwt.dto.AuthenticationDTO;
import com.projetoAuth.jwt.dto.LoginResponseDTO;
import com.projetoAuth.jwt.dto.RegisterDTO;
import com.projetoAuth.jwt.model.userModel;
import com.projetoAuth.jwt.repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth  = this.authenticationManager.authenticate(usernamePassword);
        String token = "TOKEN";

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data) {
        if (this.userRepository.findByLogin(data.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encodedPassword = this.passwordEncoder.encode(data.getPassword());
        userModel newUser = new userModel(data.getLogin(), encodedPassword, data.getRole());

        this.userRepository.save(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }
}
