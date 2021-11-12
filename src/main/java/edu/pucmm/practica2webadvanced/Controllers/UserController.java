package edu.pucmm.practica2webadvanced.Controllers;

import edu.pucmm.practica2webadvanced.Models.User;
import edu.pucmm.practica2webadvanced.Services.RolServices;
import edu.pucmm.practica2webadvanced.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserServices userServices;
    @Autowired
    private RolServices rolServices;

    @GetMapping("/auth")
    public String auth(Authentication auth, Model model, Locale locale, @RequestParam Optional<String> error) {

        model.addAttribute("title", messageSource.getMessage("title", null, locale));
        model.addAttribute("inicio", messageSource.getMessage("inicio", null, locale));
        model.addAttribute("manejarmocks", messageSource.getMessage("manejarmocks", null, locale));
        model.addAttribute("viewMocks", messageSource.getMessage("viewMocks", null, locale));
        model.addAttribute("crearMock", messageSource.getMessage("crearMock", null, locale));
        model.addAttribute("welcome", messageSource.getMessage("welcome", null, locale));
        model.addAttribute("gestionmocks", messageSource.getMessage("gestionmocks", null, locale));
        model.addAttribute("signup", messageSource.getMessage("signup", null, locale));
        model.addAttribute("createuser", messageSource.getMessage("createuser", null, locale));
        model.addAttribute("login", messageSource.getMessage("login", null, locale));
        model.addAttribute("logout", messageSource.getMessage("logout", null, locale));
        model.addAttribute("listarusers", messageSource.getMessage("listarusers", null, locale));
        model.addAttribute("admintools", messageSource.getMessage("admintools", null, locale));

        model.addAttribute("username", messageSource.getMessage("username", null, locale));
        model.addAttribute("password", messageSource.getMessage("password", null, locale));
        model.addAttribute("login", messageSource.getMessage("login", null, locale));
        model.addAttribute("noUser", messageSource.getMessage("noUser", null, locale));
        model.addAttribute("error", error);
        model.addAttribute("send", messageSource.getMessage("send", null, locale));

        try{
            User current = userServices.findByUsername(auth.getPrincipal().toString());
            model.addAttribute("usuario", current);
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));
        }catch (NullPointerException e)
        {

        }

        return "/Login";
    }

    @GetMapping("/sign-up")
    public String signup(Authentication auth, Model model, Locale locale) {

        model.addAttribute("title", messageSource.getMessage("title", null, locale));
        model.addAttribute("inicio", messageSource.getMessage("inicio", null, locale));
        model.addAttribute("manejarmocks", messageSource.getMessage("manejarmocks", null, locale));
        model.addAttribute("viewMocks", messageSource.getMessage("viewMocks", null, locale));
        model.addAttribute("crearMock", messageSource.getMessage("crearMock", null, locale));
        model.addAttribute("welcome", messageSource.getMessage("welcome", null, locale));
        model.addAttribute("gestionmocks", messageSource.getMessage("gestionmocks", null, locale));
        model.addAttribute("signup", messageSource.getMessage("signup", null, locale));
        model.addAttribute("createuser", messageSource.getMessage("createuser", null, locale));
        model.addAttribute("logout", messageSource.getMessage("logout", null, locale));
        model.addAttribute("listarusers", messageSource.getMessage("listarusers", null, locale));
        model.addAttribute("admintools", messageSource.getMessage("admintools", null, locale));

        model.addAttribute("username", messageSource.getMessage("username", null, locale));
        model.addAttribute("password", messageSource.getMessage("password", null, locale));
        model.addAttribute("login", messageSource.getMessage("login", null, locale));
        model.addAttribute("noUser", messageSource.getMessage("noUser", null, locale));
        model.addAttribute("create", messageSource.getMessage("create", null, locale));
        model.addAttribute("rol", messageSource.getMessage("rol", null, locale));

        try{
            User current = userServices.findByUsername(auth.getPrincipal().toString());
            model.addAttribute("usuario", current);
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));
        }catch (NullPointerException e)
        {

        }


        return "/CreateUser";
    }

    @PostMapping("/create")
    public String signupPost(
                             @PathParam("username") String username,
                             @PathParam("password") String password,
                             @PathParam("rol") String rol) {
        try {

            User user = new User();
            user.setUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRoles(new HashSet<>(Arrays.asList(rolServices.findByID(rol))));
            userServices.insert(user);


        }catch (NullPointerException e){

        }
        return "redirect:/user/auth";
    }


}
