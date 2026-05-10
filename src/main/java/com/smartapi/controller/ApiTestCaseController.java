package com.smartapi.controller;
import com.smartapi.entity.ApiTestCase;
import com.smartapi.service.ApiTestCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/test-cases")
public class ApiTestCaseController {
    
    private final ApiTestCaseService apiTestCaseService;
    
    public ApiTestCaseController(ApiTestCaseService apiTestCaseService){
        this.apiTestCaseService=apiTestCaseService;
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
 }