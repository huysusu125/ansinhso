package com.huytd.ansinhso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    /**
     * Bean AuditorAware cho JPA Auditing.
     * BaseEntity sẽ tự động dùng để set createdBy / updatedBy.
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            // TODO: nếu dùng Spring Security, lấy username thực từ SecurityContextHolder
            // ví dụ:
            // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            // return Optional.ofNullable(auth != null ? auth.getName() : "userdefault");

            // Tạm thời dùng giá trị mặc định
            return Optional.of("userdefault");
        };
    }
}