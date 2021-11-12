package edu.pucmm.practica2webadvanced.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AccessMethod {
    @Id
    private String method;

    public AccessMethod() {
    }

    public AccessMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
