package com.smartapi.controller;

import com.smartapi.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        model.addAttribute(
                "totalTestCases",
                dashboardService.getTotalTestCases()
        );

        model.addAttribute(
                "totalExecutions",
                dashboardService.getTotalExecutions()
        );

        model.addAttribute(
                "passedExecutions",
                dashboardService.getPassedExecutions()
        );

        model.addAttribute(
                "failedExecutions",
                dashboardService.getFailedExecutions()
        );

        model.addAttribute(
                "passPercentage",
                dashboardService.getPassPercentage()
        );

        model.addAttribute(
                "recentExecutions",
                dashboardService.getRecentExecutions()
        );

        return "dashboard";
    }
}