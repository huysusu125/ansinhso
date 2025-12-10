package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Answer option information")
public class OptionRequest {

    @Schema(
            description = "ID of the option (A, B, C, D, ...)",
            example = "A",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Option ID must not be empty")
    private String optionId;

    @Schema(
            description = "Content of the answer option",
            example = "2013",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Option content must not be empty")
    private String content;

    @Schema(
            description = "Indicates whether this option is correct",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "You must specify whether the option is correct or not")
    private Boolean isCorrect;
}
