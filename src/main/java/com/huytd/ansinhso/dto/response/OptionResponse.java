package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Answer option information")
public class OptionResponse {
    @Schema(description = "Option ID database", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Option ID", example = "A")
    private String optionId;

    @Schema(description = "Option content", example = "1992")
    private String content;

    @Schema(description = "Is this the correct answer?", example = "true")
    private boolean isCorrect;
}
