package edu.pucmm.practica2webadvanced.Services;

import edu.pucmm.practica2webadvanced.Models.User;
import edu.pucmm.practica2webadvanced.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;


    @Transactional()
    public User insert(User u)
    {
        userRepository.save(u);
        return u;
    }

    public User findByUsername(String u)
    {
        return userRepository.findByUsername(u);
    }

    public boolean authentication(String username, String password)
    {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return true;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAllActive(){
        return userRepository.findAllByActive(true);
    }
}
