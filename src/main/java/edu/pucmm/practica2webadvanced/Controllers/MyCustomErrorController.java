package edu.pucmm.practica2webadvanced.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class MyCustomErrorController implements ErrorController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, Locale locale) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//                        + "<div>Exception Message: <b>%s</b></div><body></html>",
//                statusCode, exception==null? "N/A": exception.getMessage());
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

        return "/error";
    }

}