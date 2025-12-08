package com.huytd.ansinhso.controller.cms;

import com.huytd.ansinhso.dto.response.ApiResponse;
import com.huytd.ansinhso.service.FileManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cms-api/file")
@RequiredArgsConstructor
@Tag(name = "Upload file", description = "APIs upload file for news")
public class FileController {

    private final FileManagerService fileManagerService;


    @Operation(
            summary = "Upload file to MinIO",
            description = "Upload a single file to MinIO storage. Supported file types: images, documents, videos, etc."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "File uploaded successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid file or file size exceeded"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<String>> uploadFileMinIO(
            @Parameter(
                    description = "File to upload",
                    required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("file") MultipartFile file) {
        String response = fileManagerService.uploadFile(file);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
