package edu.pucmm.practica2webadvanced.Repositories;

import edu.pucmm.practica2webadvanced.Models.AccessMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessMethodRepository extends JpaRepository<AccessMethod, String> {

    List<AccessMethod> findAll();
    AccessMethod findByMethod(String a);
}
