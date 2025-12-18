package com.huytd.ansinhso.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitQuizResponse {
    private String resultId;
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
    private double score;
    private Timestamp completedAt;
}