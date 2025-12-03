package com.huytd.ansinhso.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileManagerService {
    String uploadFile(MultipartFile file);
    String removeFile(String path);
}
