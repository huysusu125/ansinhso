package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.request.CreateTopicRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.TopicResponse;
import com.huytd.ansinhso.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms-api/topics")
@RequiredArgsConstructor
@Tag(name = "Topic Management", description = "APIs for managing news topics")
public class TopicController {

    private final TopicService topicService;

    @Operation(
            summary = "Create a new topic",
            description = "Create a new topic with the provided name. Topic name must be unique."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Topic created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or topic name already exists"
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<TopicResponse>> createTopic(
            @Valid @RequestBody CreateTopicRequest request) {
        TopicResponse response = topicService.createTopic(request);
        return ResponseEntity.ok(
                ApiResponse.success("Topic created successfully", response));
    }

    @Operation(
            summary = "Get all topics",
            description = "Retrieve a list of all topics in the system"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "List of topics retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<TopicResponse>>> getAllTopics() {
        List<TopicResponse> responses = topicService.getAllTopics();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }
}