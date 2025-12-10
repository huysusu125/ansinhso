package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.request.LoginZaloRequest;
import com.huytd.ansinhso.dto.request.RefreshTokenRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.LoginResponse;
import com.huytd.ansinhso.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("appAuthController")
@RequestMapping("/app-api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for authentication with Zalo")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "Login with Zalo",
            description = "Authenticate a user using Zalo login information and return an access token along with user details."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body or missing required fields"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized – Zalo authentication failed"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    ResponseEntity<ApiResponse<LoginResponse>> loginWithZalo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login Zalo request token request",
                    required = true
            ) @Valid @RequestBody LoginZaloRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success(authService.loginWithZalo(loginRequest)));
    }

    @Operation(
            summary = "Refresh access token",
            description = "Use refresh token to obtain a new access token and refresh token pair"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Token refreshed successfully"

            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired refresh token"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Refresh token request",
                    required = true
            )
            @Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = authService.refreshTokenCustomer(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", response));
    }
}
