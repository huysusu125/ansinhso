package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.request.SubmitQuizRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.dto.response.SubmitQuizResponse;
import com.huytd.ansinhso.dto.response.TopUserDTO;
import com.huytd.ansinhso.service.QuestionService;
import com.huytd.ansinhso.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("appQuestionController")
@RequestMapping("/app-api/questions")
@RequiredArgsConstructor
@Tag(name = "Question app", description = "APIs question for customer in mini app ")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;

    @Operation(
            summary = "Get list of questions",
            description = "Get list of questions"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Questions retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<QuestionResponse>>> getQuestions() {

        ListResponse<QuestionResponse> response = questionService.getQuestions();

        return ResponseEntity.ok(ApiResponse.success(response));
    }


    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<SubmitQuizResponse>> submitQuiz(
            @Valid @RequestBody SubmitQuizRequest request) {
        SubmitQuizResponse response = quizService.submitQuiz(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<ApiResponse<List<TopUserDTO>>> getTop5Users() {
        List<TopUserDTO> topUsers = quizService.getTop5Users();
        return ResponseEntity.ok(ApiResponse.success(topUsers));
    }
}
