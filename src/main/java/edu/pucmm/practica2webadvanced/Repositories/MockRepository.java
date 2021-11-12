package edu.pucmm.practica2webadvanced.Repositories;


import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockRepository extends JpaRepository<Mock, Long> {
    List<Mock> findAllByDeleted(boolean deleted);

    List<Mock> findAllByUserAndDeleted(User user, boolean deleted);

    Mock findByRoute(String r);

    Mock findByIdMock(long id);
}
