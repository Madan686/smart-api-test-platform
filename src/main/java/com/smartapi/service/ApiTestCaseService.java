package com.smartapi.service;
import com.smartapi.entity.ApiTestCase;
import com.smartapi.repository.ApiTestCaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ApiTestCaseService {
   private final ApiTestCaseRepository apiTestCaseRepository;
   
   public ApiTestCaseService(ApiTestCaseRepository apiTestCaseRepository){
    this.apiTestCaseRepository=apiTestCaseRepository;
   }

   public ApiTestCase saveTestCase(ApiTestCase apiTestCase){
    return apiTestCaseRepository.save(apiTestCase);
   }

   public List<ApiTestCase> getAllTestCases(){
    return apiTestCaseRepository.findAll();
   }
}
