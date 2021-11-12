package edu.pucmm.practica2webadvanced.Controllers;

import edu.pucmm.practica2webadvanced.Models.Mock;
import edu.pucmm.practica2webadvanced.Services.MockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class MockRestAPIController {

    @Autowired
    private MockServices mockServices;

    @GetMapping("/view")
    public ResponseEntity<String> mockapiGet(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("GET")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }

    @PostMapping("/view")
    public ResponseEntity<String> mockapiPost(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("POST")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }

    @PutMapping("/view")
    public ResponseEntity<String> mockapiPut(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("PUT")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }

    @PatchMapping("/view")
    public ResponseEntity<String> mockapiPatch(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("PATCH")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }

    @DeleteMapping("/view")
    public ResponseEntity<String> mockapiDelete(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("DELETE")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.OPTIONS)
    public ResponseEntity mockapiOptions(
            @PathParam("idmock") Integer idmock,
            HttpServletResponse response,
            @RequestHeader(value = "token", required = false) String token) {

        HttpHeaders headers = new HttpHeaders();
        Mock mock;

        mock = mockServices.findByID(idmock);

        if (mock.getAccessMethod().getMethod().equalsIgnoreCase("OPTIONS")) {
            Map<String, String> headersmap = mockServices.formatHeaders(mock.getHttpHeaders());
            for (String s : headersmap.keySet()) {
                headers.add(s, headersmap.get(s));

            }

            headers.setContentType(MediaType.valueOf(mock.getContentType().getDescription()));
            HttpStatus status;

            try {
                status = HttpStatus.valueOf(mock.getStatusCode().getCode());
            } catch (NullPointerException a) {
                status = HttpStatus.NOT_FOUND;
                mock.setResponseBody(mock.getResponseBody() + "\nStatus code doesn't exist. Default value swapped to 400");
                System.out.println("Status code doesn't exist. Default value swapped to 400");
            }
            return new ResponseEntity<>(mock.getResponseBody(), headers, status);

        } else {
            return new ResponseEntity<>("Wrong method used", null, 400);
        }
    }
}



