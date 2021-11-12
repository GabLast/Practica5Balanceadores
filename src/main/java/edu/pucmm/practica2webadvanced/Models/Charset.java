package edu.pucmm.practica2webadvanced.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Charset implements Serializable {
    @Id
    @Column(nullable = false)
    private String description;

    public Charset() {
    }

    public Charset(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
