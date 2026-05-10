package com.smartapi.service;

import com.smartapi.entity.TestExecutionResult;
import com.smartapi.repository.ApiTestCaseRepository;
import com.smartapi.repository.TestExecutionResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final ApiTestCaseRepository apiTestCaseRepository;

    private final TestExecutionResultRepository testExecutionResultRepository;

    public DashboardService(
            ApiTestCaseRepository apiTestCaseRepository,
            TestExecutionResultRepository testExecutionResultRepository
    ) {
        this.apiTestCaseRepository = apiTestCaseRepository;
        this.testExecutionResultRepository = testExecutionResultRepository;
    }

    public long getTotalTestCases() {
        return apiTestCaseRepository.count();
    }

    public long getTotalExecutions() {
        return testExecutionResultRepository.count();
    }

    public long getPassedExecutions() {
        return testExecutionResultRepository.countByExecutionStatus("PASS");
    }

    public long getFailedExecutions() {
        return testExecutionResultRepository.countByExecutionStatus("FAIL");
    }

    public double getPassPercentage() {

        long total = getTotalExecutions();

        if (total == 0) {
            return 0;
        }

        return ((double) getPassedExecutions() / total) * 100;
    }

    public List<TestExecutionResult> getRecentExecutions() {
        return testExecutionResultRepository
                .findTop10ByOrderByExecutedAtDesc();
    }
}