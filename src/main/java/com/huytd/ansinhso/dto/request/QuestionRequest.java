package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information for creating or updating a question")
public class QuestionRequest {

    @Schema(
            description = "Content of the question",
            example = "In what year was the Constitution of the Socialist Republic of Vietnam adopted?",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Question content must not be empty")
    @Size(min = 10, max = 1000, message = "Question content must be between 10 and 1000 characters")
    private String content;

    @Schema(
            description = "List of answer options (minimum 2 options required)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = "At least one option is required")
    @Size(min = 2, message = "At least 2 options are required")
    private List<OptionRequest> options;
}
