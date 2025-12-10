package com.huytd.ansinhso.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ZaloConfig {
    @Value("${zalopay.secret-key:A3H4LxqfI65hDPA3bpHw}")
    private String secretKey;
}

