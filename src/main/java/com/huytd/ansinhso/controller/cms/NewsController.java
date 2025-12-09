package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.constant.NewsStatus;
import com.huytd.ansinhso.dto.request.CreateNewsRequest;
import com.huytd.ansinhso.dto.request.UpdateNewsRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;
import com.huytd.ansinhso.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("cmsNewsController")
@RequestMapping("/cms-api/news")
@RequiredArgsConstructor
@Tag(name = "News Management", description = "APIs for managing news articles")
public class NewsController {

    private final NewsService newsService;

    @Operation(
            summary = "Create a new news article",
            description = "Create a new news article with DRAFT status by default"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Topic not found"
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<NewsDetailResponse>> createNews(
            @Valid @RequestBody CreateNewsRequest request) {
        NewsDetailResponse response = newsService.createNews(request);
        return ResponseEntity.ok(
                ApiResponse.success("News created successfully", response));
    }

    @Operation(summary = "Update a news article", description = "Update a news article by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> updateNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id,
            @Valid @RequestBody UpdateNewsRequest request) {
        NewsDetailResponse response = newsService.updateNews(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("News updated successfully", response));
    }

    @Operation(summary = "Delete a news article", description = "Delete a news article by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News deleted successfully", null));
    }

    @Operation(summary = "Get a news article by ID", description = "Get a news article by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> getNewsById(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.getNewsById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "Get all news articles",
            description = "Retrieve list of all news articles (without content). Pinned articles appear first."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "List retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<NewsListResponse>>> getAllNews(
            @Parameter(description = "Filter by title (partial match)")
            @RequestParam(required = false) String title,
            @Parameter(description = "Filter by topic ID")
            @RequestParam(required = false) String topicId,
            @Parameter(description = "Filter by status")
            @RequestParam(required = false) NewsStatus status,
            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Page size")
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(newsService.getAllNews(title, topicId, status, page, size)));
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> publishNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.publishNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News published successfully", response));
    }

    @PatchMapping("/{id}/unpublish")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> unpublishNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.unpublishNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News unpublished successfully", response));
    }

    @PatchMapping("/{id}/pin")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> pinNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.pinNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News pinned successfully", response));
    }

    @PatchMapping("/{id}/unpin")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> unpinNews(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.unpinNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News unpinned successfully", response));
    }
}
