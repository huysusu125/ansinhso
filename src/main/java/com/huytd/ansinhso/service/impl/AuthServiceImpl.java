package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.LoginRequest;
import com.huytd.ansinhso.dto.request.LoginZaloRequest;
import com.huytd.ansinhso.dto.response.LoginResponse;
import com.huytd.ansinhso.dto.zalo.ZaloPhoneNumberResponse;
import com.huytd.ansinhso.entity.Customer;
import com.huytd.ansinhso.entity.User;
import com.huytd.ansinhso.exception.BusinessException;
import com.huytd.ansinhso.repository.CustomerRepository;
import com.huytd.ansinhso.repository.UserRepository;
import com.huytd.ansinhso.service.AuthService;
import com.huytd.ansinhso.service.JwtService;
import com.huytd.ansinhso.service.ZaloService;
import com.huytd.ansinhso.utils.AppUtils;
import com.huytd.ansinhso.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ZaloService zaloService;
    private final CustomerRepository customerRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid username or password");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpiration)
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                .build();
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new BusinessException("Invalid or expired refresh token");
        }
        final String username = jwtService.extractSubject(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Invalid or expired refresh token"));
        String newAccessToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpiration)
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                .build();
    }

    @Override
    public LoginResponse loginWithZalo(LoginZaloRequest loginRequest) {
        ZaloPhoneNumberResponse res = zaloService.getUserInfo(loginRequest.getAccessToken(), loginRequest.getCode());
        if (res == null) {
            return null;
        }
        if (res.getError() != 0) {
            return null;
        }
        String phoneNumber = "0" + AppUtils.getLastNineCharacters(res.getData().getNumber());
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer == null) {
            var userInfo = zaloService.callZaloApi(loginRequest.getAccessToken());
            customer = customerRepository.save(Customer
                    .builder()
                    .name(userInfo.getName())
                    .birthday(TimeUtils.convertToTimestamp(userInfo.getBirthday()))
                    .avatar(userInfo.getPicture().getData().getUrl())
                    .phoneNumber(phoneNumber)
                    .build());
        }
        String accessToken = jwtService.generateToken(customer.getPhoneNumber());
        String refreshToken = jwtService.generateToken(customer.getPhoneNumber());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpiration)
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(customer.getId())
                        .username(customer.getName())
                        .build())
                .build();
    }

    @Override
    public LoginResponse refreshTokenCustomer(String refreshToken) {
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new BusinessException("Invalid or expired refresh token");
        }
        final String phoneNumber = jwtService.extractSubject(refreshToken);
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer == null) {
            throw new BusinessException("Invalid or expired refresh token");
        }

        String accessToken = jwtService.generateToken(customer.getPhoneNumber());
        refreshToken = jwtService.generateToken(customer.getPhoneNumber());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpiration)
                .userInfo(LoginResponse.UserInfo.builder()
                        .id(customer.getId())
                        .username(customer.getName())
                        .build())
                .build();
    }
}
