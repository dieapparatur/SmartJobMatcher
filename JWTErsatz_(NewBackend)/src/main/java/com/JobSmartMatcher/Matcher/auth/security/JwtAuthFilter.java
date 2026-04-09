package com.JobSmartMatcher.Matcher.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//SOME HELP FROM GPT

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTHandler jwtHandler;
    private final UserInfoHandler userInfoHandler;

    public JwtAuthFilter(JWTHandler jwtHandler, UserInfoHandler userInfoHandler) {
        this.jwtHandler = jwtHandler;
        this.userInfoHandler = userInfoHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String path = request.getServletPath();


        // skip public endpoints
        //Question is if I have to do that if they are already permitted in the SecurityConfig; maybe because of .addFilterBefore?
        if (
                        path.equals("/login/candidate") ||
                        path.equals("/login/company") ||
                        path.equals("/register/candidate") ||
                        path.equals("/register/company") ||
                        path.equals("/health")) {
            filterChain.doFilter(request, response);
            return;
        }


        String authHeader = request.getHeader("Authorization");

        for(int i = 0, i < authHeader.)

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        try {

            //die frage ist ob index 7 noch richtig ist?
            String token = authHeader.substring(7);
            String email = jwtHandler.extractEmail(token);
            String role = jwtHandler.extractRole(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userInfoHandler.loadUserByUsername(email);

                if (jwtHandler.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    System.out.println(userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing, invalid or expired JWT.");
        }
    }
}
