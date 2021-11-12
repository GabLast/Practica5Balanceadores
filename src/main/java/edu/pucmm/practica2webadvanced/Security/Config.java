package edu.pucmm.practica2webadvanced.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/mock/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/dbconsole", "/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/auth") //indicando la ruta que estaremos utilizando.
                .failureUrl("/user/auth?error")//en caso de fallar puedo indicar otra pagina.
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutSuccessUrl("/user/auth")
                .permitAll();
//                .and().addFilterBefore(new JWTAuthorization(), BasicAuthenticationFilter.class);

        //TODO: validar exclusivamente en ambiente de prueba.
        // deshabilitando las seguridad contra los frame internos.
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
