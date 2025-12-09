package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;
import com.huytd.ansinhso.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("appNewsController")
@RequestMapping("/app-api/news")
@RequiredArgsConstructor
@Tag(name = "View new articles in mini app", description = "APIs for list and detail new articles for mini app")
public class NewsController {

    private final NewsService newsService;

    @Operation(summary = "Get a news article by ID", description = "Get a news article by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsDetailResponse>> getNewsById(
            @Parameter(description = "News ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        NewsDetailResponse response = newsService.getNewsPublishById(id);
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
            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Page size")
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(ApiResponse.success(newsService.getAllNewsPublish(title, topicId, page, size)));
    }
}
