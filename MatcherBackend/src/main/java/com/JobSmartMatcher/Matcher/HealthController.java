package com.JobSmartMatcher.Matcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping(path = "/health")
    public String simpleHealthCheck() {
        return "simpleHealthCheck okay";
    }
}
