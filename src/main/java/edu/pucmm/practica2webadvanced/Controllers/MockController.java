package edu.pucmm.practica2webadvanced.Controllers;

import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Models.User;
import edu.pucmm.practica2webadvanced.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.Locale;

@Controller
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserServices userServices;
    @Autowired
    private MockServices mockServices;
    @Autowired
    private CharsetServices charsetServices;
    @Autowired
    private HTTPStatusCodeServices httpStatusCodeServices;
    @Autowired
    private ContentTypeServices contentTypeServices;
    @Autowired
    private AccessMethodServices accessMethodServices;

    @GetMapping("/list")
    public String manage(Model model, Locale locale, Authentication auth){

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

        try{
            User current = userServices.findByUsername(auth.getName());
            model.addAttribute("usuario", current);
            model.addAttribute("listmocks", mockServices.findAllNotDeletedByUser(current));
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));
        }catch (NullPointerException e)
        {
            System.out.println("No user found");
        }


        return "/users/ManageMocks";
    }

    @GetMapping("/create")
    public String createGet(Model model, Locale locale, Authentication auth){

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

        model.addAttribute("accessMethod", messageSource.getMessage("accessMethod", null, locale));
        model.addAttribute("charset", messageSource.getMessage("charset", null, locale));
        model.addAttribute("contentType", messageSource.getMessage("contentType", null, locale));
        model.addAttribute("header", messageSource.getMessage("header", null, locale));
        model.addAttribute("mockname", messageSource.getMessage("mockname", null, locale));
        model.addAttribute("statuscode", messageSource.getMessage("statuscode", null, locale));
        model.addAttribute("fechacreate", messageSource.getMessage("fechacreate", null, locale));
        model.addAttribute("fechaexpire", messageSource.getMessage("fechaexpire", null, locale));
        model.addAttribute("createMock", messageSource.getMessage("createMock", null, locale));
        model.addAttribute("create", messageSource.getMessage("create", null, locale));
        model.addAttribute("name", messageSource.getMessage("name", null, locale));
        model.addAttribute("body", messageSource.getMessage("body", null, locale));
        model.addAttribute("jsonFormat", messageSource.getMessage("jsonFormat", null, locale));
        model.addAttribute("ruta", messageSource.getMessage("ruta", null, locale));
        model.addAttribute("jwt", messageSource.getMessage("jwt", null, locale));
        model.addAttribute("keyvalue", messageSource.getMessage("keyvalue", null, locale));

        model.addAttribute("hora", messageSource.getMessage("hora", null, locale));
        model.addAttribute("dia", messageSource.getMessage("dia", null, locale));
        model.addAttribute("semana", messageSource.getMessage("semana", null, locale));
        model.addAttribute("mes", messageSource.getMessage("mes", null, locale));

        model.addAttribute("listCodes", httpStatusCodeServices.findAll());
        model.addAttribute("listCharset", charsetServices.findAll());
        model.addAttribute("listContentType", contentTypeServices.findAll());
        model.addAttribute("listAccessMethod", accessMethodServices.findAll());

        try{
            User current = userServices.findByUsername(auth.getName());
            model.addAttribute("usuario", current);
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));

        }catch (NullPointerException e)
        {
            System.out.println("No user found");
        }

        return "/users/CreateMock";
    }

    @PostMapping("/create")
    public String createPost(Authentication auth,
                             @PathParam("name") String name,
                             @PathParam("ruta") String ruta,
                             @PathParam("httpcode") Integer httpcode,
                             @PathParam("accessMethod") String accessMethod,
                             @PathParam("charset") String charset,
                             @PathParam("contentType") String contentType,
                             @RequestParam(value = "header", required = false) String header,
                             @RequestParam(value = "body", required = false) String body,
                             @PathParam("fechaexpire") String fechaexpire,
                             @PathParam("jwt") String jwt){

        User current = userServices.findByUsername(auth.getName());
        Mock mock = new Mock();
        mock.setName(name.trim());
        mock.setRoute(ruta.trim());
        mock.setStatusCode(httpStatusCodeServices.findByCode(httpcode));
        mock.setAccessMethod(accessMethodServices.findByMethod(accessMethod));
        mock.setCharset(charsetServices.findByDescription(charset));
        mock.setContentType(contentTypeServices.findByDescription(contentType));
        mock.setHttpHeaders(header.trim());
        mock.setResponseBody(body.trim());
        mock.setExpirationDate(mockServices.calcularFechaExpiracion(fechaexpire));
        mock.setUser(current);

        try {
            if(jwt.equalsIgnoreCase("true"))
            {
                mock.setToken(mockServices.generarToken(current, mock.getExpirationDate()));
            }
        }catch (NullPointerException e){
            mock.setToken("");
        }

        mockServices.insert(mock);

        return "redirect:/mock/create";
    }

    @GetMapping("/edit")
    public String editGet(Model model, Locale locale,
                          Authentication auth,
                          @PathParam("idmock") Long idmock){

        model.addAttribute("mock", mockServices.findByID(idmock));

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
        model.addAttribute("mockname", messageSource.getMessage("mockname", null, locale));
        model.addAttribute("statuscode", messageSource.getMessage("statuscode", null, locale));
        model.addAttribute("fechacreate", messageSource.getMessage("fechacreate", null, locale));
        model.addAttribute("fechaexpire", messageSource.getMessage("fechaexpire", null, locale));
        model.addAttribute("createMock", messageSource.getMessage("createMock", null, locale));
        model.addAttribute("create", messageSource.getMessage("create", null, locale));
        model.addAttribute("name", messageSource.getMessage("name", null, locale));
        model.addAttribute("body", messageSource.getMessage("body", null, locale));
        model.addAttribute("jsonFormat", messageSource.getMessage("jsonFormat", null, locale));
        model.addAttribute("ruta", messageSource.getMessage("ruta", null, locale));
        model.addAttribute("jwt", messageSource.getMessage("jwt", null, locale));
        model.addAttribute("keyvalue", messageSource.getMessage("keyvalue", null, locale));
        model.addAttribute("edit", messageSource.getMessage("edit", null, locale));

        model.addAttribute("hora", messageSource.getMessage("hora", null, locale));
        model.addAttribute("dia", messageSource.getMessage("dia", null, locale));
        model.addAttribute("semana", messageSource.getMessage("semana", null, locale));
        model.addAttribute("mes", messageSource.getMessage("mes", null, locale));

        model.addAttribute("listCodes", httpStatusCodeServices.findAll());
        model.addAttribute("listCharset", charsetServices.findAll());
        model.addAttribute("listContentType", contentTypeServices.findAll());
        model.addAttribute("listAccessMethod", accessMethodServices.findAll());

        try{
            User current = userServices.findByUsername(auth.getName());
            model.addAttribute("usuario", current);
            model.addAttribute("admin", current.getRoles().toString().contains("ADMIN"));

        }catch (NullPointerException e)
        {
            System.out.println("No user found");
        }

        return "/users/EditMock";
    }

    @PostMapping("/edit")
    public String editPost(Authentication auth,
                             @PathParam("idmock") long idmock,
                             @PathParam("name") String name,
                             @PathParam("ruta") String ruta,
                             @PathParam("httpcode") Integer httpcode,
                             @PathParam("accessMethod") String accessMethod,
                             @PathParam("charset") String charset,
                             @PathParam("contentType") String contentType,
                             @RequestParam(value = "header", required = false) String header,
                             @RequestParam(value = "body", required = false) String body,
                             @PathParam("fechaexpire") String fechaexpire,
                             @PathParam("jwt") String jwt){

        try{
            User current = userServices.findByUsername(auth.getName());
            Mock mock = mockServices.findByID(idmock);
            if (mock != null) {

                mock.setName(name.trim());
                mock.setRoute(ruta.trim());
                mock.setStatusCode(httpStatusCodeServices.findByCode(httpcode));
                mock.setAccessMethod(accessMethodServices.findByMethod(accessMethod));
                mock.setCharset(charsetServices.findByDescription(charset));
                mock.setContentType(contentTypeServices.findByDescription(contentType));
                mock.setHttpHeaders(header.trim());
                mock.setResponseBody(body.trim());
                mock.setExpirationDate(mockServices.calcularFechaExpiracion(fechaexpire));
                mock.setUser(current);

                try {
                    if(jwt.equalsIgnoreCase("true"))
                    {
                        mock.setToken(mockServices.generarToken(current, mock.getExpirationDate()));
                    }
                }catch (NullPointerException e){
                    mock.setToken("");
                }

                mockServices.insert(mock);
            }
        }catch (NullPointerException e){
            System.out.println("User or mock is null");
        }

        return "redirect:/mock/list";
    }

    @PostMapping("/delete")
    public String deleteMock(@PathParam("idmock") Long idmock){

        try{
            Mock mock = mockServices.findByID(idmock);
            mock.setDeleted(true);
            mockServices.insert(mock);
        }catch (NullPointerException e){
            System.out.println("ERROR = Mock is null");
        }

        return "redirect:/mock/list";
    }
}
