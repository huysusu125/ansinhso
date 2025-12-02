package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Schema(description = "News list item (without content)")
public class NewsListResponse {

    @Schema(description = "News ID", example = "1")
    private Long id;

    @Schema(description = "News title", example = "Breaking: New Technology Unveiled")
    private String title;

    @Schema(description = "News summary", example = "A brief overview...")
    private String summary;

    @Schema(description = "Thumbnail URL", example = "https://example.com/images/thumbnail.jpg")
    private String thumbnailUrl;

    @Schema(description = "Topic ID", example = "1")
    private Long topicId;

    @Schema(description = "Topic name", example = "Technology")
    private String topicName;

    @Schema(description = "Is pinned", example = "false")
    private Boolean isPinned;

    @Schema(description = "Status (DRAFT/PUBLISHED)", example = "PUBLISHED")
    private String status;

    @Schema(description = "Publish time", example = "2024-01-15T10:00:00")
    private Timestamp publishAt;

    @Schema(description = "View count", example = "1250")
    private Long views;

}
