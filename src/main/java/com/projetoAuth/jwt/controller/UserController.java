package com.projetoAuth.jwt.controller;

import com.projetoAuth.jwt.dto.UserProfileDTO;
import com.projetoAuth.jwt.dto.UserUpdateByAdminDTO; // Importar o novo DTO
import com.projetoAuth.jwt.model.userModel;
import com.projetoAuth.jwt.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // Manter PasswordEncoder se for usar para resetar senha (futuramente)
import org.springframework.web.bind.annotation.DeleteMapping; // Importar para DELETE
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Importar para @PathVariable
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLogin = authentication.getName();

        userModel user = userRepository.findByLogin(currentLogin);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserProfileDTO(user));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> updateMyProfile(@RequestBody UserProfileDTO updatedProfile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLogin = authentication.getName();

        userModel existingUser = userRepository.findByLogin(currentLogin);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedProfile.getLogin() != null && !updatedProfile.getLogin().isEmpty() && !updatedProfile.getLogin().equals(existingUser.getLogin())) {
            if (userRepository.existsByLogin(updatedProfile.getLogin())) {
                return ResponseEntity.badRequest().body(null);
            }
            existingUser.setLogin(updatedProfile.getLogin());
        }

        userModel savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(new UserProfileDTO(savedUser));
    }

    // NOVO: Endpoint para ADMIN visualizar usuário por ID
    @GetMapping("/{id}") // Rota: /users/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable Long id) {
        Optional<userModel> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserProfileDTO(userOptional.get()));
    }

    // NOVO: Endpoint para ADMIN editar usuário por ID
    @PutMapping("/{id}") // Rota: /users/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> updateUserById(@PathVariable Long id, @RequestBody UserUpdateByAdminDTO updatedUser) {
        Optional<userModel> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userModel existingUser = userOptional.get();

        if (updatedUser.getLogin() != null && !updatedUser.getLogin().isEmpty() && !updatedUser.getLogin().equals(existingUser.getLogin())) {
            if (userRepository.existsByLogin(updatedUser.getLogin())) {
                return ResponseEntity.badRequest().body(null);
            }
            existingUser.setLogin(updatedUser.getLogin());
        }

        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        userModel savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(new UserProfileDTO(savedUser));
    }

    // NOVO: Endpoint para ADMIN deletar usuário por ID
    @DeleteMapping("/{id}") // Rota: /users/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para sucesso sem corpo
    }
}
