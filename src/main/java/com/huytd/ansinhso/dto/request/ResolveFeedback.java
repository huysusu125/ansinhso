package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for admin resolve feedback")
public class ResolveFeedback {

    @Schema(description = "Note of admin process feedback", example = "This feedback is resolved", nullable = true)
    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;
}
