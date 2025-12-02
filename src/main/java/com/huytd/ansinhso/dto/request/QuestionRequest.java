package com.huytd.ansinhso.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Thông tin câu hỏi cần tạo hoặc cập nhật")
public class QuestionRequest {
    @Schema(description = "Nội dung câu hỏi", example = "Hiến pháp nước Cộng hòa xã hội chủ nghĩa Việt Nam được thông qua năm nào?", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    @Size(min = 10, max = 1000, message = "Nội dung câu hỏi phải từ 10-1000 ký tự")
    private String content;

    @Schema(description = "Danh sách các đáp án (tối thiểu 2 đáp án)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Phải có ít nhất một đáp án")
    @Size(min = 2, message = "Phải có ít nhất 2 đáp án")
    private List<OptionRequest> options;
}
