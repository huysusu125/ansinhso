package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.mapper.QuestionMapper;
import com.huytd.ansinhso.repository.QuestionOptionRepository;
import com.huytd.ansinhso.repository.QuestionRepository;
import com.huytd.ansinhso.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (request.getOptions() != null && !request.getOptions().isEmpty()) {
            List<QuestionOption> options = request.getOptions().stream()
                    .map(optionRequest -> {
                        QuestionOption option = new QuestionOption();
                        option.setContent(optionRequest.getContent());
                        option.setCorrect(optionRequest.isCorrect());
                        option.setQuestion(savedQuestion);
                        return option;
                    })
                    .toList();

            questionOptionRepository.saveAll(options);
            savedQuestion.setOptions(options);
        }

        return questionMapper.toResponse(savedQuestion);
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
