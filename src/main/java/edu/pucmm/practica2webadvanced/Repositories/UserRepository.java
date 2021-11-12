package edu.pucmm.practica2webadvanced.Repositories;

import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findUserByUsernameAndActive(String username, boolean active);
    User findByUsername(String username);
    List<User> findAllByActive(boolean ac);

}
