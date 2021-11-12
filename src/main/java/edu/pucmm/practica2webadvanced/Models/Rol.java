package edu.pucmm.practica2webadvanced.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rol implements Serializable {
    @Id
    private String role;

    public Rol() {

    }

    public Rol(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "role='" + role + '\'' +
                '}';
    }
}
