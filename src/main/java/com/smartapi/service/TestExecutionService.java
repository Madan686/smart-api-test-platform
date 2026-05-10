package com.smartapi.service;
import com.smartapi.entity.ApiTestCase;
import com.smartapi.entity.TestExecutionResult;
import com.smartapi.repository.TestExecutionResultRepository;
import com.smartapi.repository.ApiTestCaseRepository; 
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.Map;



@Service
public class TestExecutionService {

    private final ApiTestCaseRepository apiTestCaseRepository;

    private final TestExecutionResultRepository testExecutionResultRepository;
    private final ObjectMapper objectMapper;

    private final WebClient webClient;

    public TestExecutionService(
        ApiTestCaseRepository apiTestCaseRepository,
        TestExecutionResultRepository testExecutionResultRepository
    ){
        this.apiTestCaseRepository=apiTestCaseRepository;
        this.testExecutionResultRepository=testExecutionResultRepository;
        this.webClient=WebClient.create();
        this.objectMapper=new ObjectMapper();
    }

    public TestExecutionResult runTest(Long testCaseId){
        ApiTestCase testCase=apiTestCaseRepository.findById(testCaseId).orElseThrow(() -> new RuntimeException("Test case not found"));

        TestExecutionResult result=new TestExecutionResult();

        result.setTestCaseName(testCase.getName());
        result.setRequestUrl(testCase.getUrl());
        result.setExpectedStatusCode(testCase.getExpectedStatusCode());

        try {

    WebClient.RequestBodySpec requestSpec = webClient
            .method(org.springframework.http.HttpMethod.valueOf(testCase.getMethod()))
            .uri(testCase.getUrl());

    if (testCase.getHeaders() != null
            && !testCase.getHeaders().isBlank()) {

        Map<String, String> headersMap =
                objectMapper.readValue(
                        testCase.getHeaders(),
                        new TypeReference<Map<String, String>>() {}
                );

        headersMap.forEach(requestSpec::header);
    }

    WebClient.ResponseSpec responseSpec;

    if (testCase.getRequestBody() != null
            && !testCase.getRequestBody().isBlank()) {

        responseSpec = requestSpec
                .bodyValue(testCase.getRequestBody())
                .retrieve();

    } else {

        responseSpec = requestSpec.retrieve();
    }

    ClientResponse clientResponse = webClient
            .method(org.springframework.http.HttpMethod.valueOf(testCase.getMethod()))
            .uri(testCase.getUrl())
            .exchangeToMono(response -> response.bodyToMono(String.class)
                    .map(body -> {
                        result.setActualStatusCode(response.statusCode().value());
                        result.setResponseBody(body);
                        return response;
                    }))
            .block();

    String responseBody = result.getResponseBody();

    boolean statusMatches =
            result.getActualStatusCode()
                    .equals(testCase.getExpectedStatusCode());

    boolean keywordMatches = true;

    if (testCase.getExpectedResponseKeyword() != null
            && !testCase.getExpectedResponseKeyword().isBlank()) {

        keywordMatches =
                responseBody.contains(
                        testCase.getExpectedResponseKeyword()
                );
    }

    if (statusMatches && keywordMatches) {
        result.setExecutionStatus("PASS");
    } else {
        result.setExecutionStatus("FAIL");
    }

    } catch (Exception ex) {

    result.setExecutionStatus("FAIL");
    result.setErrorMessage(ex.getMessage());
    }
     return testExecutionResultRepository.save(result);
    }
    
}
