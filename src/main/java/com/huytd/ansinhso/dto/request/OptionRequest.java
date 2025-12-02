package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Thông tin đáp án")
public class OptionRequest {

    @Schema(description = "ID của đáp án (A, B, C, D, ...)", example = "A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "ID đáp án không được để trống")
    private String optionId;

    @Schema(description = "Nội dung đáp án", example = "2013", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nội dung đáp án không được để trống")
    private String content;

    @Schema(description = "Đáp án có đúng hay không", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Phải xác định đáp án đúng/sai")
    private Boolean isCorrect;
}
