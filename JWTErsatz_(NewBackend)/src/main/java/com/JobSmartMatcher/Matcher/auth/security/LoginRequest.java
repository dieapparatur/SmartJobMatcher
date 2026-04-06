package com.JobSmartMatcher.Matcher.auth.security;

import lombok.Setter;
import lombok.Getter;

public class LoginRequest {
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

}
