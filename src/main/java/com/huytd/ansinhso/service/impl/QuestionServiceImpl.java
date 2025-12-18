package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.OptionResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.entity.Question;
import com.huytd.ansinhso.entity.QuestionOption;
import com.huytd.ansinhso.exception.ResourceNotFoundException;
import com.huytd.ansinhso.mapper.QuestionMapper;
import com.huytd.ansinhso.repository.QuestionOptionRepository;
import com.huytd.ansinhso.repository.QuestionRepository;
import com.huytd.ansinhso.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<QuestionOption> options = createQuestionOptions(request, savedQuestion);

        return questionMapper.toResponse(savedQuestion, options);
    }

    private List<QuestionOption> createQuestionOptions(QuestionRequest request, Question savedQuestion) {
        List<QuestionOption> options = request.getOptions().stream()
                .map(optionRequest -> {
                    QuestionOption option = new QuestionOption();
                    option.setContent(optionRequest.getContent());
                    option.setIsCorrect(optionRequest.getIsCorrect());
                    option.setQuestionId(savedQuestion.getId());
                    option.setOptionId(optionRequest.getOptionId());
                    return option;
                })
                .toList();

        return questionOptionRepository.saveAll(options);
    }

    @Override
    @Transactional
    public QuestionResponse updateQuestion(String id, QuestionRequest request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        question.setContent(request.getContent());
        questionOptionRepository.deleteByQuestionId(question.getId());

        List<QuestionOption> options = createQuestionOptions(request, question);
        return questionMapper.toResponse(question, options);
    }

    @Override
    @Transactional
    public void deleteQuestion(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        questionOptionRepository.deleteByQuestionId(question.getId());
        questionRepository.deleteById(question.getId());
    }

    @Override
    public ListResponse<QuestionResponse> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return ListResponse.of(List.of(), 0L);
        }
        List<QuestionOption> allOptions = questionOptionRepository.findAll();
        Map<String, List<QuestionOption>> optionsMap = allOptions.stream()
                .collect(Collectors.groupingBy(QuestionOption::getQuestionId));
        return  ListResponse.of(questions.stream().map(question -> mapToDTO(question, optionsMap.get(question.getId()))).toList(), (long) questions.size());
    }
    private QuestionResponse mapToDTO(Question question, List<QuestionOption> options) {
        List<OptionResponse> optionDTOs = options == null ? Collections.emptyList() :
                options.stream()
                        .map(this::mapOptionToDTO)
                        .collect(Collectors.toList());

        return QuestionResponse.builder()
                .id(question.getId())
                .content(question.getContent())
                .options(optionDTOs)
                .build();
    }

    private OptionResponse mapOptionToDTO(QuestionOption option) {
        return OptionResponse.builder()
                .id(option.getId())
                .optionId(option.getOptionId())
                .content(option.getContent())
                .isCorrect(option.getIsCorrect())
                .build();
    }
}
