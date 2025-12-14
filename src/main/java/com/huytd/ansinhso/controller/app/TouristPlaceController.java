package com.huytd.ansinhso.controller.app;

import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.dto.response.TouristPlaceResponse;
import com.huytd.ansinhso.service.TouristPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("appTouristPlaceController")
@RequestMapping("/app-api/tourist-places")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Tourist Place",
        description = "API bản đồ du lịch"
)
public class TouristPlaceController {

    private final TouristPlaceService service;

    @GetMapping
    @Operation(
            summary = "Lấy danh sách địa điểm du lịch",
            description = """
                    Lấy danh sách địa điểm du lịch.
                    """
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lấy danh sách thành công")
    })
    public ResponseEntity<ApiResponse<List<TouristPlaceResponse>>> getAllTouristPlaces() {

        List<TouristPlaceResponse> response = service.getAllPublishedTouristPlaces();
        return ResponseEntity.ok(ApiResponse.success("...", response));
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
}
