package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Question summary information (used for list view)")
public class QuestionListResponse {
    @Schema(description = "Question ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Question content", example = "In which year was the Constitution of the Socialist Republic of Vietnam adopted?")
    private String content;
}
