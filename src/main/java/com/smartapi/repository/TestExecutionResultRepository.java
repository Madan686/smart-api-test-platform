package com.smartapi.repository;
import com.smartapi.entity.TestExecutionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestExecutionResultRepository extends JpaRepository <TestExecutionResult, Long>{
    
}
