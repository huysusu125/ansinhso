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
import org.springframework.web.bind.annotation.*;

@RestController
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

    @Operation(
            summary = "Update an existing news article",
            description = "Update news article information by ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News or Topic not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> updateNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id,
            @Valid @RequestBody UpdateNewsRequest request) {
        NewsDetailResponse response = newsService.updateNews(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("News updated successfully", response));
    }

    @Operation(
            summary = "Delete a news article",
            description = "Permanently delete a news article by ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News deleted successfully", null));
    }

    @Operation(
            summary = "Get news detail by ID",
            description = "Retrieve full details of a news article including content"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> getNewsById(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
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
            @RequestParam(required = false) Long topicId,
            @Parameter(description = "Filter by status")
            @RequestParam(required = false) NewsStatus status,
            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Page size")
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(newsService.getAllNews(title, topicId, status, page, size)));
    }

    @Operation(
            summary = "Publish a news article",
            description = "Change news status from DRAFT to PUBLISHED. Sets publish time if not already set."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News published successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "News is already published"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @PatchMapping("/{id}/publish")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> publishNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
        NewsDetailResponse response = newsService.publishNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News published successfully", response));
    }

    @Operation(
            summary = "Unpublish a news article",
            description = "Change news status from PUBLISHED to DRAFT"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News unpublished successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "News is already unpublished"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @PatchMapping("/{id}/unpublish")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> unpublishNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
        NewsDetailResponse response = newsService.unpublishNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News unpublished successfully", response));
    }

    @Operation(
            summary = "Pin a news article",
            description = "Pin a news article to display it at the top of lists"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News pinned successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "News is already pinned"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @PatchMapping("/{id}/pin")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> pinNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
        NewsDetailResponse response = newsService.pinNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News pinned successfully", response));
    }

    @Operation(
            summary = "Unpin a news article",
            description = "Remove pin from a news article"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "News unpinned successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "News is not pinned"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "News not found"
            )
    })
    @PatchMapping("/{id}/unpin")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> unpinNews(
            @Parameter(description = "News ID", required = true, example = "1")
            @PathVariable Long id) {
        NewsDetailResponse response = newsService.unpinNews(id);
        return ResponseEntity.ok(
                ApiResponse.success("News unpinned successfully", response));
    }
}
