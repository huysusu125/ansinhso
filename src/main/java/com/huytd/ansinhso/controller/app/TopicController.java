package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.TopicResponse;
import com.huytd.ansinhso.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("appTopicController")
@RequestMapping("/app-api/topics")
@RequiredArgsConstructor
@Tag(name = "App - Topics", description = "APIs for users to view news topics")
public class TopicController {

    private final TopicService topicService;;

    @Operation(
            summary = "Get all topics",
            description = "Retrieve a list of all news topics available in the system"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Topics retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<TopicResponse>>> getAllTopics() {
        List<TopicResponse> responses = topicService.getAllTopics();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }
}
