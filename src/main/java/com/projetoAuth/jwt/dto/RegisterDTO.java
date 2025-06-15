package com.projetoAuth.jwt.dto;

import com.projetoAuth.jwt.model.userRole;

public class RegisterDTO {
    private String login;
    private String password;
    private userRole role;

    public RegisterDTO() {
    }

    public RegisterDTO(String login, String password, userRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userRole getRole() {
        return role;
    }

    public void setRole(userRole role) {
        this.role = role;
    }
}
