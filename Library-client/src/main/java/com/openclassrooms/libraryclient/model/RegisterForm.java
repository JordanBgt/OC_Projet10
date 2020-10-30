package com.openclassrooms.libraryclient.model;

import java.util.ArrayList;
import java.util.List;

public class RegisterForm {

    private String username;
    private String password;
    private String email;
    private List<String> role = new ArrayList<>();

    public RegisterForm() {
        this.role.add("user");
    }

    public RegisterForm(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role.add("user");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
