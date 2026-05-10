package com.smartapi.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="test_execution_results")
public class TestExecutionResult {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String testCaseName;

    private String requestUrl;
    
    private Integer expectedStatusCode;
    
    private Integer actualStatusCode;
    
    private String executionStatus;

    @Column(columnDefinition="TEXT")
    private String responseBody;

    @Column(columnDefinition="TEXT")
    private String errorMessage;

    private LocalDateTime executedAt;

    public TestExecutionResult(){
        this.executedAt=LocalDateTime.now();
    }

    public long getId(){
        return id;
    }
    public String getTestCaseName(){
        return testCaseName;
    }
    public void setTestCaseName(String testCaseName){
        this.testCaseName=testCaseName;
    }

    public String  getRequestUrl(){
        return requestUrl;
    }
    public void setRequestUrl(String requestUrl){
        this.requestUrl=requestUrl;
    }

    public Integer getExpectedStatusCode(){
        return expectedStatusCode;
    } 
    public void setExpectedStatusCode(Integer expectedStatusCode){
        this.expectedStatusCode=expectedStatusCode;
    }

    public Integer getActualStatusCode(){
        return actualStatusCode;
    }
    public void setActualStatusCode(Integer actualStatusCode){
        this.actualStatusCode=actualStatusCode;
    }
    
    public String getExecutionStatus(){
        return executionStatus;
    }
    public void setExecutionStatus(String executionStatus){
        this.executionStatus=executionStatus;
    }
    
    public String getResponseBody(){
        return responseBody;
    }
    public void setResponseBody(String responseBody){
        this.responseBody=responseBody;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage=errorMessage;
    }

    public LocalDateTime getExecutedAt(){
        return executedAt;
    }
}
