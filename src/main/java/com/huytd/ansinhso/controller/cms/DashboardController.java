package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.FeedbackCategoryCountResponse;
import com.huytd.ansinhso.dto.response.FeedbackStatusCountResponse;
import com.huytd.ansinhso.dto.response.LocationTopView;
import com.huytd.ansinhso.dto.response.NewsListResponse;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import com.huytd.ansinhso.service.NewsService;
import com.huytd.ansinhso.service.TouristPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("cmsDashboardController")
@RequestMapping("/cms-api/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Dashboard",
        description = "API thống kê của CMS"
)
public class DashboardController {

    private final NewsService newsService;
    private final CitizenFeedbackService citizenFeedbackService;
    private final TouristPlaceService touristPlaceService;

    @Operation(
            summary = "Lấy top 10 bài viết có lượt xem cao nhất",
            description = "API trả về danh sách 10 bài viết có lượt xem (views) cao nhất, "
                    + "chỉ bao gồm các bài đã được publish."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy danh sách top 10 bài viết thành công"
            )
    })
    @GetMapping("/top-view")
    public ResponseEntity<ApiResponse<List<NewsListResponse>>> getTop10ViewedNews() {
        return ResponseEntity.ok(ApiResponse.success(newsService.getTop10Viewed()));
    }


    @GetMapping("/statistic/feedback/status")
    @Operation(
            summary = "Thống kê feedback theo trạng thái",
            description = "API trả về số lượng feedback cho từng trạng thái (PENDING, IN_PROGRESS, RESOLVED, REJECTED)"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy thống kê thành công"
            )
    })
    public ResponseEntity<ApiResponse<List<FeedbackStatusCountResponse>>> countByStatus() {
        return ResponseEntity.ok(ApiResponse.success(citizenFeedbackService.countFeedbackByStatus()));
    }

    @GetMapping("/statistic/feedback/category")
    @Operation(
            summary = "Thống kê feedback theo trạng thái",
            description = "API trả về số lượng feedback cho từng thể loại"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy thống kê thành công"
            )
    })
    public ResponseEntity<ApiResponse<List<FeedbackCategoryCountResponse>>> countByCategory() {
        return ResponseEntity.ok(ApiResponse.success(citizenFeedbackService.countByCategory()));
    }

    @GetMapping("/statistic/location/view")
    @Operation(
            summary = "Lấy top 10 địa điểm có lượt xem cao nhất",
            description = "API trả về danh sách 10 địa điểm có lượt xem cao nhất, "
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy thống kê thành công"
            )
    })
    public ResponseEntity<ApiResponse<List<LocationTopView>>> getTop10ViewedLocation() {
        return ResponseEntity.ok(ApiResponse.success(touristPlaceService.getTop10Viewed()));
    }
}
