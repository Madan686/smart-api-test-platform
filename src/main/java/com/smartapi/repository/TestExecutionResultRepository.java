package com.smartapi.repository;

import com.smartapi.entity.TestExecutionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestExecutionResultRepository
        extends JpaRepository<TestExecutionResult, Long> {

    long countByExecutionStatus(String executionStatus);

    List<TestExecutionResult> findTop10ByOrderByExecutedAtDesc();
}