package edu.pucmm.practica2webadvanced.Services;

import com.google.common.base.Splitter;
import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Models.User;
import edu.pucmm.practica2webadvanced.Repositories.MockRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MockServices {
    @Autowired
    private MockRepository mockRepository;
    @Value("${SECRET_KEY_JWT}")
    private String SECRET_KEY_JWT;

    @Transactional()
    public Mock insert(Mock h){
        mockRepository.save(h);
        return h;
    }

    public List<Mock> findAllNotDeletedByUser(User user){
        return mockRepository.findAllByUserAndDeleted(user, false);
    }

    public List<Mock> findAllNotDeleted(){
        return mockRepository.findAllByDeleted(false);
    }

    public Mock findByRoute(String r){
        return mockRepository.findByRoute(r);
    }

    public Mock findByID(long id){
        return mockRepository.findByIdMock(id);
    }

    public Date calcularFechaExpiracion(String valor)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        if(valor.equalsIgnoreCase("hour"))
        {
            cal.add(Calendar.HOUR_OF_DAY, 1);
        }else if(valor.equalsIgnoreCase("day")){
            cal.add(Calendar.DATE, 1);
        }else if(valor.equalsIgnoreCase("week")){
            cal.add(Calendar.DATE, 7);
        }else if(valor.equalsIgnoreCase("month")){
            cal.add(Calendar.DATE, 30);
        }else{
            cal.add(Calendar.DATE, 1);
        }

        Date fechaexp = cal.getTime();

        return fechaexp;


    }

    public String generarToken(User usuario, Date expire) {

        String token = Jwts
                .builder()
                .setId("JWTGEN")
                .setSubject(usuario.getUsername())
                .claim("roles", usuario.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY_JWT.getBytes()).compact();

        return token;
    }

    public Map<String, String> formatHeaders(String headers) {

        Map<String, String> map = Splitter.on(",").trimResults().withKeyValueSeparator('=').split(headers);

//        for(String s : map.keySet()){
//            System.out.printf(s + map.get(s));
//        }
        //prints key = value
        return map;
    }
}
