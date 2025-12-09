package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("appCitizenFeedbackController")
@RequestMapping("/app-api/news")
@RequiredArgsConstructor
@Tag(name = "", description = "")
public class CitizenFeedbackController {

    private final CitizenFeedbackService feedbackService;

    @Operation(summary = "Submit feedback", description = "Người dân gửi ý kiến phản ánh")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Gửi phản ánh thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<CitizenFeedbackResponse>> submitFeedback(
            @Valid @RequestBody CitizenFeedbackRequest request) {

        CitizenFeedbackResponse response = feedbackService.submitFeedback(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
