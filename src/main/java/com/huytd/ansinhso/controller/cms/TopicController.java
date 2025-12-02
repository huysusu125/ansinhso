package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.request.CreateTopicRequest;
import com.huytd.ansinhso.dto.request.UpdateTopicRequest;
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
//    @PostMapping
    public ResponseEntity<ApiResponse<TopicResponse>> createTopic(
            @Valid @RequestBody CreateTopicRequest request) {
        TopicResponse response = topicService.createTopic(request);
        return ResponseEntity.ok(
                ApiResponse.success("Topic created successfully", response));
    }

    @Operation(
            summary = "Update an existing topic",
            description = "Update topic information by ID. Topic name must be unique."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Topic updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or topic name already exists"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Topic not found"
            )
    })
//    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicResponse>> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTopicRequest request) {
        TopicResponse response = topicService.updateTopic(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Topic updated successfully", response));
    }

    @Operation(
            summary = "Delete a topic",
            description = "Delete a topic by ID. Cannot delete if topic is being used by news articles."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Topic deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Topic is being used by news articles"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Topic not found"
            )
    })
//    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok(
                ApiResponse.success("Topic deleted successfully", null));
    }

    @Operation(
            summary = "Get topic by ID",
            description = "Retrieve detailed information of a topic by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Topic found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Topic not found"
            )
    })
//    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicResponse>> getTopicById(@PathVariable Long id) {
        TopicResponse response = topicService.getTopicById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
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