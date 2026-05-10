package com.smartapi.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="api_test_cases")
public class ApiTestCase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String method;
    private String url;

    @Column(columnDefinition = "TEXT")
    private String headers;

    @Column(columnDefinition="TEXT")
    private String requestBody;

    private Integer expectedStatusCode;
    
    private String expectedResponseKeyword; 

    private LocalDateTime createdAt;

    public ApiTestCase() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }

    public String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method=method;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getHeaders(){
        return headers;
    }
    
    public void setHeaders(String headers){
        this.headers=headers;
    }

    public String getRequestBody(){
        return requestBody;
    }

    public void setRequestBody(String requestBody){
        this.requestBody=requestBody;
    }

    public Integer getExpectedStatusCode(){
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(Integer expectedStatusCode){
        this.expectedStatusCode=expectedStatusCode;
    }

    public String getExpectedResponseKeyword(){
        return expectedResponseKeyword;
    }

    public void setExpectedResponseKeyword(String expectedResponseKeyword){
        this.expectedResponseKeyword=expectedResponseKeyword;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}
