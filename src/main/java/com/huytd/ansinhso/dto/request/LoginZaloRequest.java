package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request payload for logging in with Zalo")
public class LoginZaloRequest {

    @Schema(
            description = "Access token returned by Zalo after the user authenticates through the app",
            example = "abcd1234efgh5678"
    )
    @NotBlank(message = "Access token is required")
    private String accessToken;

    @Schema(
            description = "Authorization code returned by Zalo as part of the OAuth flow",
            example = "xyz987654321"
    )
    @NotBlank(message = "Authorization code is required")
    private String code;
}
