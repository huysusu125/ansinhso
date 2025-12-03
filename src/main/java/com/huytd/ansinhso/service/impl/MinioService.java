package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.config.MinioConfig;
import com.huytd.ansinhso.entity.FileManager;
import com.huytd.ansinhso.repository.FileManagerRepository;
import com.huytd.ansinhso.service.FileManagerService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioService implements FileManagerService {

    private final MinioClient minioClient;

    private final MinioConfig minioConfig;
    private final FileManagerRepository fileManagerRepository;

    @Override
    public String uploadFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            fileManagerRepository.save(FileManager.builder().path(fileName).build());
            log.info("Uploaded file successfully to MinIO: {}", fileName);
            return minioConfig.getMinioUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO", e);
        }
    }

    @Override
    public String removeFile(String path) {
        return null;
    }
}
