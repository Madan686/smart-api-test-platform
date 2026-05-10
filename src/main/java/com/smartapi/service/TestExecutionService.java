package com.smartapi.service;
import com.smartapi.entity.ApiTestCase;
import com.smartapi.entity.TestExecutionResult;
import com.smartapi.repository.TestExecutionResultRepository;
import com.smartapi.repository.ApiTestCaseRepository; 
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestExecutionService {

    private final ApiTestCaseRepository apiTestCaseRepository;

    private final TestExecutionResultRepository testExecutionResultRepository;

    private final WebClient webClient;

    public TestExecutionService(
        ApiTestCaseRepository apiTestCaseRepository,
        TestExecutionResultRepository testExecutionResultRepository
    ){
        this.apiTestCaseRepository=apiTestCaseRepository;
        this.testExecutionResultRepository=testExecutionResultRepository;
        this.webClient=WebClient.create();
    }

    public TestExecutionResult runTest(Long testCaseId){
        ApiTestCase testCase=apiTestCaseRepository.findById(testCaseId).orElseThrow(() -> new RuntimeException("Test case not found"));

        TestExecutionResult result=new TestExecutionResult();

        result.setTestCaseName(testCase.getName());
        result.setRequestUrl(testCase.getUrl());
        result.setExpectedStatusCode(testCase.getExpectedStatusCode());

        try{

            String responseBody =webClient
                            .method(org.springframework.http.HttpMethod.valueOf(testCase.getMethod()))
                            .uri(testCase.getUrl())
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

            result.setResponseBody(responseBody);

            int actualStatusCode = 200;
            result.setActualStatusCode(actualStatusCode);

            boolean statusMatches= actualStatusCode == testCase.getExpectedStatusCode();

            boolean keywordMatches = true;

            if(testCase.getExpectedResponseKeyword()!= null && !testCase.getExpectedResponseKeyword().isBlank()){
                keywordMatches= responseBody.contains(testCase.getExpectedResponseKeyword());
            }

            if(statusMatches && keywordMatches){
                result.setExecutionStatus("PASS");
            }else{
                result.setExecutionStatus("FAIL");
            }

        }catch(Exception ex){
                result.setExecutionStatus("FAIL");
                result.setErrorMessage(ex.getMessage());
            }
            return testExecutionResultRepository.save(result);
    }
    
}
