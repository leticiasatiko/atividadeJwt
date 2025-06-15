package com.projetoAuth.jwt.dto;

import com.projetoAuth.jwt.model.userRole;

public class UserUpdateByAdminDTO {
    private String login;
    private userRole role;

    public UserUpdateByAdminDTO() {}

    public UserUpdateByAdminDTO(String login, userRole role) {
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public userRole getRole() {
        return role;
    }

    public void setRole(userRole role) {
        this.role = role;
    }
}
