package edu.pucmm.practica2webadvanced.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Mock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMock;
    @Column(columnDefinition = "varchar(255) default 'Default name'")
    private String name;
    @Column(unique = true, nullable = false)
    private String route;

    @OneToOne(cascade = CascadeType.ALL)
    private HTTPStatusCode statusCode;
    @OneToOne(cascade = CascadeType.ALL)
    private Charset charset;
    @OneToOne(cascade = CascadeType.ALL)
    private ContentType contentType;
    @OneToOne(cascade = CascadeType.ALL)
    private AccessMethod accessMethod;

    @Column(nullable = false)
    private String httpHeaders;

    @Column(nullable = false)
    private String responseBody;

    private Date dateCreate = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    private String token;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Mock() {
    }

    public long getIdMock() {
        return idMock;
    }

    public void setIdMock(long idResponse) {
        this.idMock = idResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public HTTPStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HTTPStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public AccessMethod getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(AccessMethod accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(String httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
