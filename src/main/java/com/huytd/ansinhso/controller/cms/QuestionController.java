package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms-api/questions")
@RequiredArgsConstructor
@Tag(name = "Question Management", description = "APIs for managing legal quiz questions")
public class QuestionController {

    private final QuestionService questionService;

    @Operation(
            summary = "Create a new question",
            description = "Create a new multiple-choice question with options. At least one correct answer is required."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Question created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> createQuestion(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Question information to create",
                    required = true
            )
            @Valid @RequestBody QuestionRequest request) {

        QuestionResponse response = questionService.createQuestion(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Update a question",
            description = "Update question information by ID. Can update partial or full information."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Question updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Question not found",
                    content = @Content(mediaType = "application/json")
            )
    })

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> updateQuestion(
            @Parameter(description = "Question ID to update", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Question information to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = QuestionRequest.class))
            )
            @Valid @RequestBody QuestionRequest request) {

        QuestionResponse response = questionService.updateQuestion(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Delete a question",
            description = "Permanently delete a question and all related options by ID"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Question deleted successfully",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Question not found",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(
            @Parameter(description = "Question ID to delete", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {

        questionService.deleteQuestion(id);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @Operation(
            summary = "Get question details",
            description = "Get detailed information of a question including all options"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Question retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Question not found",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> getQuestionById(
            @Parameter(description = "Question ID to retrieve", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {

        QuestionResponse response = questionService.getQuestionById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Get list of questions",
            description = "Get list of questions with filtering, searching, pagination and sorting options. " +
                    "Supports filtering by tags and searching by question content."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Questions retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<QuestionListResponse>>> getQuestions(
            @Parameter(description = "Search by question content", example = "Constitution")
            @RequestParam(required = false) String search) {

        ListResponse<QuestionListResponse> response = questionService.getQuestions(search);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
