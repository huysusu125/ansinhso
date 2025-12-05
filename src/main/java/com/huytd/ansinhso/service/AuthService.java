package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.LoginRequest;
import com.huytd.ansinhso.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(String refreshToken);
}
