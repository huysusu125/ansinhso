package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;

public interface QuestionService {
    QuestionResponse createQuestion(QuestionRequest request);
    QuestionResponse updateQuestion(String id, QuestionRequest request);
    void deleteQuestion(String id);
    QuestionResponse getQuestionById(String id);
    ListResponse<QuestionListResponse> getQuestions(String search);
}
