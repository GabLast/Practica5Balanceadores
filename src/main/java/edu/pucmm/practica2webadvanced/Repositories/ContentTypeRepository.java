package edu.pucmm.practica2webadvanced.Repositories;

import edu.pucmm.practica2webadvanced.Models.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTypeRepository extends JpaRepository<ContentType, String> {

    ContentType findByDescription(String description);
}
