package com.huytd.ansinhso.service;

import com.huytd.ansinhso.config.ZaloConfig;
import com.huytd.ansinhso.dto.zalo.CustomerZaloInfo;
import com.huytd.ansinhso.dto.zalo.ZaloPhoneNumberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZaloService {

    private final ZaloConfig config;

    private final RestTemplate restTemplate;

    public CustomerZaloInfo callZaloApi(String accessToken) {
        String url = "https://graph.zalo.me/v2.0/me?fields=id,name,birthday,picture&access_token=" + accessToken;
        ResponseEntity<CustomerZaloInfo> response = restTemplate.getForEntity(url, CustomerZaloInfo.class);
        return response.getBody();
    }

    public ZaloPhoneNumberResponse getUserInfo(String accessToken, String code) {
        String url = "https://graph.zalo.me/v2.0/me/info";

        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", accessToken);
        headers.set("code", code);
        headers.set("secret_key", config.getSecretKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ZaloPhoneNumberResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ZaloPhoneNumberResponse.class);
        log.info("ZaloPhoneNumberResponse: {}", response.getBody());
        return response.getBody();
    }
}
