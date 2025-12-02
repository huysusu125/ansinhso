package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Thông tin đáp án")
public class OptionResponse {

    @Schema(description = "ID của đáp án trong database", example = "opt_123456")
    private String id;

    @Schema(description = "ID hiển thị của đáp án (A, B, C, D, ...)", example = "A")
    private String optionId;

    @Schema(description = "Nội dung đáp án", example = "2013")
    private String content;

    @Schema(description = "Đáp án có đúng hay không", example = "true")
    private Boolean isCorrect;
}
