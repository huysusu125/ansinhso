package com.huytd.ansinhso.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Thông tin chi tiết câu hỏi")
public class QuestionResponse {
    @Schema(description = "ID của câu hỏi", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Nội dung câu hỏi", example = "Hiến pháp nước Cộng hòa xã hội chủ nghĩa Việt Nam được thông qua năm nào?")
    private String content;

    @Schema(description = "Danh sách các đáp án")
    private List<OptionResponse> options;
}
