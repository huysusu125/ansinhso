package com.huytd.ansinhso.dto.response;

import com.huytd.ansinhso.constant.FeedbackStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "CitizenFeedbackResponse",
        description = "Represents a citizen's feedback returned to the client"
)
public class CitizenFeedbackResponse {

    @Schema(description = "Unique identifier of the feedback", example = "1001")
    private String id;

    @Schema(description = "Full name of the citizen who submitted the feedback", example = "John Doe")
    private String fullName;

    @Schema(description = "Phone number of the citizen", example = "0987654321")
    private String phoneNumber;

    @Schema(description = "Address of the citizen", example = "123 Main Street, District 1, Ho Chi Minh City")
    private String address;

    @Schema(description = "Title of the feedback", example = "Noise pollution in residential area")
    private String title;

    @Schema(description = "Detailed content of the feedback",
            example = "There has been loud noise near my house every night...")
    private String content;

    @Schema(description = "Feedback category",
            example = "Environmental sanitation violation")
    private String category;

    @Schema(description = "Current status of the feedback", example = "PENDING")
    private FeedbackStatus status;

    @Schema(description = "Timestamp when the feedback was created",
            example = "2024-01-15T08:30:00")
    private Timestamp createdAt;

    @Schema(description = "List of attachment file URLs",
            example = "[\"https://example.com/file1.jpg\", \"https://example.com/file2.png\"]")
    private List<String> attachmentUrls;
}
