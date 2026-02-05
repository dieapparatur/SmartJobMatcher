package com.JobSmartMatcher.Matcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String simpleHealthCheck() {
        return "Simple Health Check Ding Ding Ding";
    }
}