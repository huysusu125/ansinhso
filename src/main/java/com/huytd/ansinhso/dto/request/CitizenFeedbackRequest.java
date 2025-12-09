package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for citizen feedback")
public class CitizenFeedbackRequest {

    @Schema(description = "Full name of submitter", example = "Nguyen Van A", maxLength = 100, nullable = true)
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @Schema(description = "Phone number (digits only, max 11 chars)", example = "0912345678", maxLength = 11, nullable = true)
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    @Size(max = 11, message = "Phone number must not exceed 11 characters")
    private String phoneNumber;

    @Schema(description = "Contact address", example = "123 ABC Street, District 1, HCMC", maxLength = 500, nullable = true)
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    @Schema(description = "Feedback title", example = "Damaged road needs repair", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Schema(description = "Detailed feedback content", example = "The road from 100-200 ABC Street is severely damaged...", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content must not exceed 1000 characters")
    private String content;

    @Schema(description = "FeedbackCategory feedback content", example = "The road from 100-200 ABC Street is severely damaged...", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "FeedbackCategory is required")
    private String category;

    @Schema(description = "Attachment URLs (maximum 5 files)", example = "[\"https://example.com/img1.jpg\"]", nullable = true)
    @Size(max = 5, message = "Maximum 5 attachments allowed")
    private List<String> attachmentUrls;

}
