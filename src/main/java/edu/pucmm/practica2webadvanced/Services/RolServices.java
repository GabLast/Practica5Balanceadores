package edu.pucmm.practica2webadvanced.Services;
import edu.pucmm.practica2webadvanced.Models.Rol;
import edu.pucmm.practica2webadvanced.Repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServices {

    @Autowired
    private RolRepository rolRepository;

    public Rol insert(Rol r){

        rolRepository.save(r);
        return r;
    }

    public List<Rol> findall(){
        return rolRepository.findAll();
    }

    public Rol findByID(String a){
        return rolRepository.findByRole(a);
    }

}
