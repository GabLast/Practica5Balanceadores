package edu.pucmm.practica2webadvanced.Repositories;

import edu.pucmm.practica2webadvanced.Models.HTTPStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HTTPStatusCodeRepository extends JpaRepository<HTTPStatusCode, Integer> {

    HTTPStatusCode findByCode(Integer integer);
}
