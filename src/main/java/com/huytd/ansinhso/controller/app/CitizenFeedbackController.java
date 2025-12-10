package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("appCitizenFeedbackController")
@RequestMapping("/app-api/feedback")
@RequiredArgsConstructor
@Tag(
        name = "Citizen Feedback",
        description = "APIs for citizens to submit and manage feedback"
)
public class CitizenFeedbackController {

    private final CitizenFeedbackService feedbackService;

    @Operation(
            summary = "Submit feedback",
            description = "Allows a citizen to submit a feedback report"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Feedback submitted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data"
            )
    })
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> submitFeedback(
            @Valid @RequestBody CitizenFeedbackRequest request) {

        CitizenFeedbackResponse response = feedbackService.submitFeedback(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Get feedback list",
            description = "Returns a paginated list of citizen feedback"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved feedback list"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid query parameters")
    })
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<ListResponse<FeedbackListResponse>>> getFeedbackList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(ApiResponse.success(feedbackService.getAllFeedback(page, size)));
    }

    @Operation(
            summary = "Get feedback details",
            description = "Retrieve detailed information of a citizen feedback by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Feedback retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Feedback not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> getFeedbackById(
            @Parameter(
                    description = "Unique identifier of the feedback",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable String id) {
        return ResponseEntity.ok(
                ApiResponse.success(feedbackService.getFeedbackById(id))
        );
    }
}
