package com.huytd.ansinhso.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitQuizRequest {

    @NotEmpty(message = "Answers list cannot be empty")
    private List<AnswerItem> answers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerItem {
        @NotBlank(message = "Question ID is required")
        private String questionId;

        @NotBlank(message = "Selected option ID is required")
        private String selectedOptionId;
    }
}
