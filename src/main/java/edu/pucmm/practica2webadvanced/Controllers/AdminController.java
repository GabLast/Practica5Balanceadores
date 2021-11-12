package edu.pucmm.practica2webadvanced.Controllers;

import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Models.User;
import edu.pucmm.practica2webadvanced.Services.MockServices;
import edu.pucmm.practica2webadvanced.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MessageSource messageSource;

    @Autowired private UserServices userServices;
    @Autowired private MockServices mockServices;

    @GetMapping("/users/list")
    public String listusers(Authentication auth, Model model, Locale locale){

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
        model.addAttribute("rol", messageSource.getMessage("rol", null, locale));
        model.addAttribute("logout", messageSource.getMessage("logout", null, locale));

        try{
            User current = userServices.findByUsername(auth.getName());
            model.addAttribute("usuario", current);
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));
            model.addAttribute("listUsers", userServices.findAllActive());

        }catch (NullPointerException e)
        {
            System.out.println("No user found");
            return "redirect:/user/auth";
        }


        return "/admin/ManageUsers";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@PathParam("username") String username){

        try {
            User user = userServices.findByUsername(username);
            user.setActive(false);
            userServices.insert(user);
        }catch (NullPointerException e){
            System.out.println("User to be deleted was found to be NULL");
        }

        return "redirect:/admin/users/list";
    }

    @GetMapping("/mocks/list")
    public String adminlistmocks(Model model, Locale locale, Authentication auth){

        model.addAttribute("title", messageSource.getMessage("title", null, locale));
        model.addAttribute("inicio", messageSource.getMessage("inicio", null, locale));
        model.addAttribute("manejar-mocks", messageSource.getMessage("manejarmocks", null, locale));
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


        model.addAttribute("accessMethod", messageSource.getMessage("accessMethod", null, locale));
        model.addAttribute("charset", messageSource.getMessage("charset", null, locale));
        model.addAttribute("contentType", messageSource.getMessage("contentType", null, locale));
        model.addAttribute("header", messageSource.getMessage("header", null, locale));
        model.addAttribute("ruta", messageSource.getMessage("ruta", null, locale));
        model.addAttribute("mockname", messageSource.getMessage("mockname", null, locale));
        model.addAttribute("statuscode", messageSource.getMessage("statuscode", null, locale));
        model.addAttribute("fechaexpire", messageSource.getMessage("fechaexpire", null, locale));
        model.addAttribute("fechacreate", messageSource.getMessage("fechacreate", null, locale));
        model.addAttribute("createMock", messageSource.getMessage("createMock", null, locale));
        model.addAttribute("create", messageSource.getMessage("create", null, locale));
        model.addAttribute("name", messageSource.getMessage("name", null, locale));
        model.addAttribute("body", messageSource.getMessage("body", null, locale));
        model.addAttribute("empty", messageSource.getMessage("empty", null, locale));
        model.addAttribute("owner", messageSource.getMessage("owner", null, locale));

        try{
            User current = userServices.findByUsername(auth.getName());
            model.addAttribute("usuario", current);
            model.addAttribute("listmocks", mockServices.findAllNotDeleted());
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));
        }catch (NullPointerException e)
        {
            System.out.println("No user found");
        }


        return "/admin/ManageMocksAdmin";
    }

    @PostMapping("/mocks/delete")
    public String admindeletemock(@PathParam("idmock") Long idmock){

        try{
            Mock mock = mockServices.findByID(idmock);
            mock.setDeleted(true);
            mockServices.insert(mock);
        }catch (NullPointerException e){
            System.out.println("ERROR = Mock is null");
        }

        return "redirect:/admin/mocks/list";
    }
}
