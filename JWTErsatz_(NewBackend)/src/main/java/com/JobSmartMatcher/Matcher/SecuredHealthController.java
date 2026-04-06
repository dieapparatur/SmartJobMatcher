package com.JobSmartMatcher.Matcher;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React
public class SecuredHealthController {

    @GetMapping("/secured")
    public String securedHealthCheck() {
        return "Secured Health CheckBLABLABLA";
    }


}
