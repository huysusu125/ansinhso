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
@Schema(description = "Request body for creating a new news article")
public class CreateNewsRequest {

    @Schema(description = "News title", example = "Breaking: New Technology Unveiled", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Schema(description = "News summary", example = "A brief overview of the new technology...")
    private String summary;

    @Schema(description = "Full content of the news", example = "Detailed article content...", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Content is required")
    private String content;

    @Schema(description = "Thumbnail URL", example = "https://example.com/images/thumbnail.jpg")
    private String thumbnailUrl;

    @Schema(description = "Topic ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Topic ID is required")
    private String topicId;

}
