package edu.pucmm.practica2webadvanced.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
public class User implements Serializable {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active = true;
//    @Column(columnDefinition="tinyint(1) default 0")
//    private boolean admin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) @Column(nullable = false)
    private Set<Rol> roles;

    public User() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {

//        if(roles.toString().contains("ADMIN")){
//            this.admin = true;
//        }
        this.roles = roles;
    }

}
