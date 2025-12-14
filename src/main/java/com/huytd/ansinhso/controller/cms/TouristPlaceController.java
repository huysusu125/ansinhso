package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.request.CreateTouristPlaceRequest;
import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.TouristPlaceResponse;
import com.huytd.ansinhso.service.TouristPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("cmsTouristPlaceController")
@RequestMapping("/cms-api/tourist-places")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Tourist Place",
        description = "API quản lý địa điểm du lịch"
)
public class TouristPlaceController {

    private final TouristPlaceService service;

    @PostMapping
    @Operation(
            summary = "Tạo mới địa điểm du lịch",
            description = "Tạo mới một địa điểm du lịch với các thông tin cơ bản như tên, địa chỉ, vị trí, danh mục..."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tạo địa điểm thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu đầu vào không hợp lệ")
    })
    public ResponseEntity<ApiResponse<TouristPlaceResponse>> createTouristPlace(
            @Valid @RequestBody CreateTouristPlaceRequest request) {

        log.info("POST /api/v1/tourist-places - Creating tourist place");

        TouristPlaceResponse response = service.createTouristPlace(request);

        return ResponseEntity.ok(
                ApiResponse.success("Tourist place created successfully", response));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Lấy chi tiết địa điểm du lịch",
            description = "Lấy thông tin chi tiết của một địa điểm du lịch theo ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lấy thông tin thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy địa điểm")
    })
    public ResponseEntity<ApiResponse<TouristPlaceResponse>> getTouristPlace(
            @Parameter(
                    description = "ID của địa điểm du lịch",
                    example = "64e7a0a1c9f1a12abc123456"
            )
            @PathVariable String id) {

        log.info("GET /api/v1/tourist-places/{} - Fetching tourist place", id);

        TouristPlaceResponse response = service.getTouristPlace(id);

        return ResponseEntity.ok(
                ApiResponse.success("Tourist place retrieved successfully", response));
    }

    @GetMapping
    @Operation(
            summary = "Lấy danh sách địa điểm du lịch",
            description = """
                    Lấy danh sách địa điểm du lịch.
                    - Có thể lọc theo `category`
                    - Có thể tìm kiếm theo từ khóa `search`
                    - Nếu không truyền tham số, trả về toàn bộ danh sách
                    """
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lấy danh sách thành công")
    })
    public ResponseEntity<ApiResponse<List<TouristPlaceResponse>>> getAllTouristPlaces(
            @Parameter(
                    description = "Danh mục địa điểm du lịch",
                    example = "historical"
            )
            @RequestParam(required = false) String category,

            @Parameter(
                    description = "Từ khóa tìm kiếm theo tên hoặc địa chỉ",
                    example = "đền"
            )
            @RequestParam(required = false) String search) {

        log.info("GET /api/v1/tourist-places - Fetching tourist places");

        List<TouristPlaceResponse> response;

        if (StringUtils.isNotBlank(category)) {
            response = service.getTouristPlacesByCategory(category);
        } else if (search != null) {
            response = service.searchTouristPlaces(search);
        } else {
            response = service.getAllTouristPlaces();
        }

        return ResponseEntity.ok(
                ApiResponse.success("Tourist places retrieved successfully", response));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Cập nhật địa điểm du lịch",
            description = "Cập nhật thông tin của một địa điểm du lịch theo ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cập nhật thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy địa điểm"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    public ResponseEntity<ApiResponse<TouristPlaceResponse>> updateTouristPlace(
            @Parameter(
                    description = "ID của địa điểm du lịch",
                    example = "64e7a0a1c9f1a12abc123456"
            )
            @PathVariable String id,

            @Valid @RequestBody CreateTouristPlaceRequest request) {

        log.info("PUT /api/v1/tourist-places/{} - Updating tourist place", id);

        TouristPlaceResponse response = service.updateTouristPlace(id, request);

        return ResponseEntity.ok(
                ApiResponse.success("Tourist place updated successfully", response));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Xóa địa điểm du lịch",
            description = "Xóa một địa điểm du lịch theo ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Xóa thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy địa điểm")
    })
    public ResponseEntity<ApiResponse<Void>> deleteTouristPlace(
            @Parameter(
                    description = "ID của địa điểm du lịch",
                    example = "64e7a0a1c9f1a12abc123456"
            )
            @PathVariable String id) {

        log.info("DELETE /api/v1/tourist-places/{} - Deleting tourist place", id);

        service.deleteTouristPlace(id);

        return ResponseEntity.ok(
                ApiResponse.success("Tourist place deleted successfully", null));
    }
}