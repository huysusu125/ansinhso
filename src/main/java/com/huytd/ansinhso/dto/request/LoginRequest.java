package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Login request containing user credentials")
public class LoginRequest {
    @NotBlank(message = "Username is required")
    @Schema(description = "Username for authentication", example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password for authentication", example = "password123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}