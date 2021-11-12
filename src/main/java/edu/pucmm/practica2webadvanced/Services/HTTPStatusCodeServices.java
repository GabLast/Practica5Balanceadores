package edu.pucmm.practica2webadvanced.Services;
import edu.pucmm.practica2webadvanced.Models.HTTPStatusCode;
import edu.pucmm.practica2webadvanced.Repositories.HTTPStatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HTTPStatusCodeServices {

    @Autowired
    private HTTPStatusCodeRepository httpStatusCodeRepository;

    @Transactional()
    public HTTPStatusCode insert(HTTPStatusCode h)
    {
        httpStatusCodeRepository.save(h);
        return h;
    }

    public HTTPStatusCode findByCode(int h)
    {

        return httpStatusCodeRepository.findByCode(h);
    }

    public List<HTTPStatusCode> findAll(){
        return httpStatusCodeRepository.findAll();
    }
}
