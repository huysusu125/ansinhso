package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for updating an existing news article")
public class UpdateNewsRequest {
    @Schema(description = "News title", example = "Updated: Technology News", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Schema(description = "News summary", example = "Updated summary...")
    @Size(max = 500, message = "Summary must not exceed 500 characters")
    private String summary;

    @Schema(description = "Full content of the news", example = "Updated content...", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Content is required")
    private String content;

    @Schema(description = "Thumbnail URL", example = "https://example.com/images/new-thumbnail.jpg")
    private String thumbnailUrl;

    @Schema(description = "Topic ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Topic ID is required")
    private Long topicId;
}
