package com.huytd.ansinhso.filter;

import com.huytd.ansinhso.entity.User;
import com.huytd.ansinhso.service.JwtService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(0)
public class AppAuthenticationFilter implements Filter {

    private final JwtService jwtService;
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/app-api/auth/login",
            "/app-api/auth/refresh-token"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI().toLowerCase();
        log.info("JWT Filter - Request to URI: {}", uri);
        if (!uri.startsWith("/app-api/") || isExcludedPath(uri)) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                if (jwtService.isTokenValid(token)) {
                    String phoneNumber = jwtService.extractSubject(token);
                    log.info("Valid token for customer: {}", phoneNumber);
                    User customer = User.builder()
                            .username(phoneNumber)
                            .build();
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    customer,
                                    null,
                                    null
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.warn("Invalid or expired token for URI: {}", uri);
                }
            } catch (Exception e) {
                log.error("Token validation failed: {}", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isExcludedPath(String path) {
        return EXCLUDED_PATHS.stream().anyMatch(path::equals);
    }
}
