package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for updating an existing topic")
public class UpdateTopicRequest {

    @Schema(description = "Topic name", example = "Sports", required = true)
    @NotBlank(message = "Topic name is required")
    @Size(min = 2, max = 255, message = "Topic name must be between 2 and 255 characters")
    private String name;
}
