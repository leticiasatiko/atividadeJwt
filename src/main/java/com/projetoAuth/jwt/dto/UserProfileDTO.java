package com.projetoAuth.jwt.dto;

import com.projetoAuth.jwt.model.userModel;
import com.projetoAuth.jwt.model.userRole;

public class UserProfileDTO {
    private Long id;
    private String login;
    private userRole role;

    public UserProfileDTO() {}

    public UserProfileDTO(userModel user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
