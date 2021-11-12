package edu.pucmm.practica2webadvanced.Services;

import edu.pucmm.practica2webadvanced.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class DBData {

    @Autowired
    private AccessMethodServices accessMethodServices;
    @Autowired
    private CharsetServices charsetServices;
    @Autowired
    private UserServices userServices;
    @Autowired
    private HTTPStatusCodeServices httpStatusCodeServices;
    @Autowired
    private ContentTypeServices contentTypeServices;
    @Autowired
    private RolServices rolServices;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void initDB()
    {
        //roles
        Rol admin = new Rol("ROLE_ADMIN");
        Rol user = new Rol("ROLE_USER");
        rolServices.insert(admin);
        rolServices.insert(user);
        //users
        User user1 = new User();
        User user2 = new User();
        user1.setUsername("admin");
        user1.setPassword(bCryptPasswordEncoder.encode("admin"));
        user1.setRoles(new HashSet<>(Arrays.asList(admin, user)));
        user2.setUsername("gab");
        user2.setPassword(bCryptPasswordEncoder.encode("123"));
        user2.setRoles(new HashSet<>(Arrays.asList(user)));
        userServices.insert(user1);
        userServices.insert(user2);

        //Methods
        accessMethodServices.insert(new AccessMethod("GET"));
        accessMethodServices.insert(new AccessMethod("POST"));
        accessMethodServices.insert(new AccessMethod("PUT"));
        accessMethodServices.insert(new AccessMethod("PATCH"));
        accessMethodServices.insert(new AccessMethod("DELETE"));
        accessMethodServices.insert(new AccessMethod("OPTIONS"));

        //HTTP Status Codes
        httpStatusCodeServices.insert(new HTTPStatusCode(100, "Continue"));
        httpStatusCodeServices.insert(new HTTPStatusCode(101, "Switching Protocols"));
        httpStatusCodeServices.insert(new HTTPStatusCode(200, "OK"));
        httpStatusCodeServices.insert(new HTTPStatusCode(201, "Created"));
        httpStatusCodeServices.insert(new HTTPStatusCode(202, "Accepted"));
        httpStatusCodeServices.insert(new HTTPStatusCode(203, "Non-Authoritative Information"));
        httpStatusCodeServices.insert(new HTTPStatusCode(204, "No Content"));
        httpStatusCodeServices.insert(new HTTPStatusCode(205, "Reset Content"));
        httpStatusCodeServices.insert(new HTTPStatusCode(206, "Partial Content"));
        httpStatusCodeServices.insert(new HTTPStatusCode(300, "Multiple Choices"));
        httpStatusCodeServices.insert(new HTTPStatusCode(301, "Moved Permanently"));
        httpStatusCodeServices.insert(new HTTPStatusCode(302, "Found"));
        httpStatusCodeServices.insert(new HTTPStatusCode(303, "See Other"));
        httpStatusCodeServices.insert(new HTTPStatusCode(304, "Not Modified"));
        httpStatusCodeServices.insert(new HTTPStatusCode(305, "Use Proxy"));
        httpStatusCodeServices.insert(new HTTPStatusCode(307, "Temporary Redirect"));
        httpStatusCodeServices.insert(new HTTPStatusCode(400, "Bad Request"));
        httpStatusCodeServices.insert(new HTTPStatusCode(401, "Unauthorized"));
        httpStatusCodeServices.insert(new HTTPStatusCode(402, "Payment Required"));
        httpStatusCodeServices.insert(new HTTPStatusCode(403, "Forbidden"));
        httpStatusCodeServices.insert(new HTTPStatusCode(404, "Not Found"));
        httpStatusCodeServices.insert(new HTTPStatusCode(405, "Method Not Allowed"));
        httpStatusCodeServices.insert(new HTTPStatusCode(406, "Not Acceptable"));
        httpStatusCodeServices.insert(new HTTPStatusCode(407, "Proxy Authentication Required"));
        httpStatusCodeServices.insert(new HTTPStatusCode(408, "Request Timeout"));
        httpStatusCodeServices.insert(new HTTPStatusCode(409, "Conflict"));
        httpStatusCodeServices.insert(new HTTPStatusCode(410, "Gone"));
        httpStatusCodeServices.insert(new HTTPStatusCode(411, "Length Required"));
        httpStatusCodeServices.insert(new HTTPStatusCode(412, "Precondition Failed"));
        httpStatusCodeServices.insert(new HTTPStatusCode(413, "Reqeust Entity Too Large"));
        httpStatusCodeServices.insert(new HTTPStatusCode(414, "Request URI Too Long"));
        httpStatusCodeServices.insert(new HTTPStatusCode(415, "Unsupported Media Type"));
        httpStatusCodeServices.insert(new HTTPStatusCode(416, "Request Range Not Satisfiable"));
        httpStatusCodeServices.insert(new HTTPStatusCode(417, "Expectation Failed"));
        httpStatusCodeServices.insert(new HTTPStatusCode(500, "Internal Server Error"));
        httpStatusCodeServices.insert(new HTTPStatusCode(501, "Not Implemented"));
        httpStatusCodeServices.insert(new HTTPStatusCode(502, "Bad Gateway"));
        httpStatusCodeServices.insert(new HTTPStatusCode(503, "Service Unavailable"));
        httpStatusCodeServices.insert(new HTTPStatusCode(504, "Gateway Timeout"));
        httpStatusCodeServices.insert(new HTTPStatusCode(505, "HTTP Version Not Supported"));

        //charset
        charsetServices.insert(new Charset("UTF-8"));
        charsetServices.insert(new Charset("ISO-8859-1"));
        charsetServices.insert(new Charset("UTF-16"));

        //content type
        contentTypeServices.insert(new ContentType("application/json"));
        contentTypeServices.insert(new ContentType("application/x-www-form-urlencoded"));
        contentTypeServices.insert(new ContentType("application/xhtml+xml"));
        contentTypeServices.insert(new ContentType("application/xml"));
        contentTypeServices.insert(new ContentType("multipart/form-data"));
        contentTypeServices.insert(new ContentType("text/css"));
        contentTypeServices.insert(new ContentType("text/csv"));
        contentTypeServices.insert(new ContentType("text/html"));
        contentTypeServices.insert(new ContentType("text/json"));
        contentTypeServices.insert(new ContentType("text/plain"));
        contentTypeServices.insert(new ContentType("text/xml"));

        System.out.println("Initial Data has been loaded");

    }
}
