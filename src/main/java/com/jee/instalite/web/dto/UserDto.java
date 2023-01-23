package com.jee.instalite.web.dto;

import com.jee.instalite.model.Role;

import java.util.List;

public class UserDto {
    private String username;
    private String email ;

    private String password ;

    private List<Role> roles ;


    public UserDto(String username, String email, String password , List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles ;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
