package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Topic information response")
public class TopicResponse {

    @Schema(description = "Topic ID", example = "123fsdfsdf")
    private String id;

    @Schema(description = "Topic name", example = "Technology")
    private String name;

}
