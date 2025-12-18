package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;

public interface QuestionService {
    QuestionResponse createQuestion(QuestionRequest request);
    QuestionResponse updateQuestion(String id, QuestionRequest request);
    void deleteQuestion(String id);
    ListResponse<QuestionResponse> getQuestions();

}
