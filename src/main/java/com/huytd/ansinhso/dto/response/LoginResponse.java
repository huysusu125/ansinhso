package com.huytd.ansinhso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Login response containing authentication tokens and user information")
public class LoginResponse {
    @Schema(description = "JWT access token for API authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "JWT refresh token to obtain new access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
    
    @Schema(description = "Type of token (always Bearer)", example = "Bearer", defaultValue = "Bearer")
    private String tokenType = "Bearer";
    
    @Schema(description = "Token expiration time in seconds", example = "3600")
    private Long expiresIn;
    
    @Schema(description = "Authenticated user information")
    private UserInfo userInfo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Basic user information")
    public static class UserInfo {
        @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
        private String id;
        
        @Schema(description = "Username", example = "admin")
        private String username;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "PhoneNumber only zalo mini app", example = "0987654159")
        private String phoneNumber;
    }
}
