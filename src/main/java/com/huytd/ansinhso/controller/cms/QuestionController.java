package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Tag(name = "Question Management", description = "API quản lý câu hỏi trắc nghiệm pháp luật")
public class QuestionController {

    private final QuestionService questionService;

    @Operation(
            summary = "Tạo câu hỏi mới",
            description = "Tạo một câu hỏi trắc nghiệm mới với các đáp án. Ít nhất phải có một đáp án đúng."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Tạo câu hỏi thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Dữ liệu không hợp lệ",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Lỗi server",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<QuestionResponse>> createQuestion(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Thông tin câu hỏi cần tạo",
                    required = true
            )
            @Valid @RequestBody QuestionRequest request) {

        QuestionResponse response = questionService.createQuestion(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Cập nhật câu hỏi",
            description = "Cập nhật thông tin câu hỏi theo ID. Có thể cập nhật một phần hoặc toàn bộ thông tin."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Dữ liệu không hợp lệ",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy câu hỏi",
                    content = @Content(mediaType = "application/json")
            )
    })

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> updateQuestion(
            @Parameter(description = "ID của câu hỏi cần cập nhật", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Thông tin câu hỏi cần cập nhật",
                    required = true,
                    content = @Content(schema = @Schema(implementation = QuestionRequest.class))
            )
            @Valid @RequestBody QuestionRequest request) {

        QuestionResponse response = questionService.updateQuestion(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Xóa câu hỏi",
            description = "Xóa vĩnh viễn câu hỏi và tất cả các đáp án liên quan theo ID"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Xóa thành công",
                    content = @Content(mediaType = "application/json")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy câu hỏi",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(
            @Parameter(description = "ID của câu hỏi cần xóa", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {

        questionService.deleteQuestion(id);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @Operation(
            summary = "Lấy chi tiết câu hỏi",
            description = "Lấy thông tin chi tiết của một câu hỏi bao gồm tất cả các đáp án"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy thông tin thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy câu hỏi",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionResponse>> getQuestionById(
            @Parameter(description = "ID của câu hỏi cần lấy", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {

        QuestionResponse response = questionService.getQuestionById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Lấy danh sách câu hỏi",
            description = "Lấy danh sách câu hỏi với các tùy chọn lọc, tìm kiếm, phân trang và sắp xếp. " +
                    "Hỗ trợ lọc theo tags và tìm kiếm theo nội dung câu hỏi."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy danh sách thành công"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Tham số không hợp lệ",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<QuestionListResponse>>> getQuestions(
            @Parameter(description = "Tìm kiếm theo nội dung câu hỏi", example = "Hiến pháp")
            @RequestParam(required = false) String search) {

        ListResponse<QuestionListResponse> response = questionService.getQuestions(search);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
