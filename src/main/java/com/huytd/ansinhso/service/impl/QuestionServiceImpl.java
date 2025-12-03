package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.entity.Question;
import com.huytd.ansinhso.entity.QuestionOption;
import com.huytd.ansinhso.mapper.QuestionMapper;
import com.huytd.ansinhso.repository.QuestionOptionRepository;
import com.huytd.ansinhso.repository.QuestionRepository;
import com.huytd.ansinhso.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final QuestionMapper questionMapper;

    @Override
    @Transactional
    public QuestionResponse createQuestion(QuestionRequest request) {
        // Tạo câu hỏi mới
        Question question = new Question();
        question.setContent(request.getContent());

        // Lưu câu hỏi
        Question savedQuestion = questionRepository.save(question);

        // Tạo các đáp án
        List<QuestionOption> options = request.getOptions().stream()
                .map(optionRequest -> {
                    QuestionOption option = new QuestionOption();
                    option.setContent(optionRequest.getContent());
                    option.setCorrect(optionRequest.getIsCorrect());
                    option.setQuestionId(savedQuestion.getId());
                    return option;
                })
                .toList();

        questionOptionRepository.saveAll(options);


        return questionMapper.toResponse(savedQuestion, options);
    }

    @Override
    public QuestionResponse updateQuestion(String id, QuestionRequest request) {
        return null;
    }

    @Override
    public void deleteQuestion(String id) {

    }

    @Override
    public QuestionResponse getQuestionById(String id) {
        return null;
    }

    @Override
    public ListResponse<QuestionListResponse> getQuestions(String search) {
        return null;
    }
}
