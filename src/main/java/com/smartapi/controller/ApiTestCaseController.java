package com.smartapi.controller;
import com.smartapi.entity.ApiTestCase;
import com.smartapi.service.ApiTestCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.smartapi.service.TestExecutionService;
import com.smartapi.entity.TestExecutionResult;

@Controller
@RequestMapping("/test-cases")
public class ApiTestCaseController {
    
    private final ApiTestCaseService apiTestCaseService;
    private final TestExecutionService testExecutionService;
    
    public ApiTestCaseController(ApiTestCaseService apiTestCaseService, TestExecutionService testExecutionService){
        this.apiTestCaseService=apiTestCaseService;
        this.testExecutionService=testExecutionService;
    }

    @GetMapping
    public String listTestCases(Model model){
        model.addAttribute("testCases", apiTestCaseService.getAllTestCases());
        return "test-cases/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("apiTestCase", new ApiTestCase());
        return "test-cases/form";
    }

    @PostMapping
    public String saveTestCase(@ModelAttribute ApiTestCase apiTestCase){
        apiTestCaseService.saveTestCase(apiTestCase);
        return "redirect:/test-cases";
    }

    @PostMapping("/run/{id}")
    public String runTest(@PathVariable Long id,Model model) {
    TestExecutionResult result =testExecutionService.runTest(id);
    model.addAttribute("result", result);

    return "test-cases/result";
}
 }