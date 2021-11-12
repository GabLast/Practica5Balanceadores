package edu.pucmm.practica2webadvanced.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorization extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    @Value("${SECRET_KEY_JWT}")
    private String SECRET_KEY_JWT;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().startsWith("/api/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getServletPath().startsWith("/api/")){
            if (existeJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
                filterChain.doFilter(request, response);
            }else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Sin permisos");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET_KEY_JWT.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {

        List authorities = (List) claims.get("roles");
        List<SimpleGrantedAuthority> listaAuto = new ArrayList<>();
        listaAuto.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,listaAuto);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }
}
