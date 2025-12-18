package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.SubmitQuizRequest;
import com.huytd.ansinhso.dto.response.SubmitQuizResponse;
import com.huytd.ansinhso.dto.response.TopUserDTO;
import com.huytd.ansinhso.entity.QuestionOption;
import com.huytd.ansinhso.entity.QuizResult;
import com.huytd.ansinhso.exception.BusinessException;
import com.huytd.ansinhso.repository.QuestionOptionRepository;
import com.huytd.ansinhso.repository.QuestionRepository;
import com.huytd.ansinhso.repository.QuizResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository optionRepository;

    @Transactional
    public SubmitQuizResponse submitQuiz(SubmitQuizRequest request) {
        int correctCount = 0;
        int totalQuestions = request.getAnswers().size();

        // Duyệt qua từng câu trả lời
        for (SubmitQuizRequest.AnswerItem item : request.getAnswers()) {
            // Validate câu hỏi tồn tại
            questionRepository.findById(item.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found: " + item.getQuestionId()));

            // Validate option tồn tại
            QuestionOption selectedOption = optionRepository.findById(item.getSelectedOptionId())
                    .orElseThrow(() -> new RuntimeException("Option not found: " + item.getSelectedOptionId()));

            // Kiểm tra option có thuộc câu hỏi này không
            if (!selectedOption.getQuestionId().equals(item.getQuestionId())) {
                throw new BusinessException("Option does not belong to question: " + item.getQuestionId());
            }

            // Tính điểm
            if (selectedOption.getIsCorrect()) {
                correctCount++;
            }
        }

        // Tính điểm phần trăm
        int score = correctCount;

        // Lưu kết quả
        QuizResult result = QuizResult.builder()
                .phoneNumber(getPhoneNumberOfCustomer())
                .totalQuestions(totalQuestions)
                .correctAnswers(correctCount)
                .score(score)
                .completedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        result = quizResultRepository.save(result);

        // Trả về response
        return SubmitQuizResponse.builder()
                .resultId(result.getId())
                .totalQuestions(totalQuestions)
                .correctAnswers(correctCount)
                .wrongAnswers(totalQuestions - correctCount)
                .score(score)
                .completedAt(result.getCompletedAt())
                .build();
    }

    private String getPhoneNumberOfCustomer() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .filter(auth -> !"anonymousUser".equals(auth.getName()))
                .map(Authentication::getName)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<TopUserDTO> getTop5Users() {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> results = quizResultRepository.findTop5UsersByBestScore(topFive);

        return results.stream()
                .map(row -> TopUserDTO.builder()
                        .phoneNumber(maskPhoneNumber((String) row[0]))
                        .bestScore((Integer) row[1])
                        .lastCompletedAt((Timestamp) row[3])
                        .build())
                .collect(Collectors.toList());
    }

    private static String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return phoneNumber;
        }

        // Loại bỏ khoảng trắng và ký tự đặc biệt
        String cleanPhone = phoneNumber.replaceAll("[^0-9]", "");

        // Nếu số điện thoại quá ngắn (ít hơn 7 số) thì không ẩn
        if (cleanPhone.length() <= 6) {
            return phoneNumber;
        }

        // Lấy 3 số đầu
        String prefix = cleanPhone.substring(0, 3);

        // Lấy 3 số cuối
        String suffix = cleanPhone.substring(cleanPhone.length() - 3);

        // Tính số ký tự cần ẩn
        int maskedLength = cleanPhone.length() - 6;

        // Tạo chuỗi dấu *
        String masked = "*".repeat(maskedLength);

        return prefix + masked + suffix;
    }
}
