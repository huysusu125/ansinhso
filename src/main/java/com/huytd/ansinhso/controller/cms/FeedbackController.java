package com.huytd.ansinhso.controller.cms;


import com.huytd.ansinhso.constant.FeedbackStatus;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("feedbackController")
@RequestMapping("/cms-api/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback Management", description = "APIs for managing feedback")
public class FeedbackController {

    private final CitizenFeedbackService feedbackService;

    @Operation(
            summary = "Get all feedback",
            description = "Retrieve list of all feedback."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "List retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<FeedbackListResponse>>> getAllFeedbacks(
            @Parameter(description = "Filter by title (partial match)")
            @RequestParam(required = false) String title,
            @Parameter(description = "Filter by fullName (partial match)")
            @RequestParam(required = false) String fullName,
            @Parameter(description = "Filter by category (partial match)")
            @RequestParam(required = false) String category,
            @Parameter(description = "Filter by status")
            @RequestParam(required = false) FeedbackStatus status,
            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Page size")
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(feedbackService.getAllFeedback(title, fullName, category, status, page, size)));
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


    @Operation(
            summary = "Accept feedback",
            description = "Mark the feedback as IN_PROGRESS to indicate that it is being processed"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Feedback accepted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Feedback not found")
    })
    @PutMapping("/{id}/accept")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> acceptFeedback(
            @Parameter(description = "Feedback ID", required = true)
            @PathVariable String id) {

        return ResponseEntity.ok(ApiResponse.success(
                feedbackService.acceptFeedback(id)
        ));
    }

    @Operation(
            summary = "Resolve feedback",
            description = "Mark the feedback as RESOLVED after processing is completed"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Feedback resolved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Feedback not found")
    })
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> resolveFeedback(
            @Parameter(description = "Feedback ID", required = true)
            @PathVariable String id) {

        return ResponseEntity.ok(ApiResponse.success(
                feedbackService.resolveFeedback(id)
        ));
    }

    @Operation(
            summary = "Reject feedback",
            description = "Mark the feedback as REJECTED"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Feedback rejected successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Feedback not found")
    })
    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> rejectFeedback(
            @Parameter(description = "Feedback ID", required = true)
            @PathVariable String id) {

        return ResponseEntity.ok(ApiResponse.success(
                feedbackService.rejectFeedback(id)
        ));
    }

}
