package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.LoginRequest;
import com.huytd.ansinhso.dto.request.LoginZaloRequest;
import com.huytd.ansinhso.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(String refreshToken);

    LoginResponse loginWithZalo(LoginZaloRequest loginRequest);

    LoginResponse refreshTokenCustomer(String refreshToken);
}
