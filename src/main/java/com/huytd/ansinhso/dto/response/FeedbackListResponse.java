package com.huytd.ansinhso.dto.response;

import com.huytd.ansinhso.constant.FeedbackStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Schema(
        name = "FeedbackListResponse",
        description = "Represents summary information of a citizen feedback item in a list"
)
public class FeedbackListResponse {

    @Schema(description = "Unique identifier of the feedback", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Full name of the citizen who submitted the feedback", example = "John Doe")
    private String fullName;

    @Schema(description = "Phone number of the citizen", example = "0987654321")
    private String phoneNumber;

    @Schema(description = "Address of the citizen", example = "123 Main Street, District 1")
    private String address;

    @Schema(description = "Title of the feedback", example = "Environmental pollution near residential area")
    private String title;

    @Schema(description = "Current status of the feedback", example = "PENDING")
    private FeedbackStatus status;

    @Schema(description = "Short content or summary of the feedback", example = "There is waste being dumped near my neighborhood...")
    private String content;

    @Schema(description = "Feedback category", example = "Environmental sanitation violation")
    private String category;

    @Schema(description = "Feedback time created", example = "123456 ")
    private Timestamp createdAt;

    @Schema(description = "Note of admin process feedback", example = "This feedback is resolve", nullable = true)
    private String note;

}
