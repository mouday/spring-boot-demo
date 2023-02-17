package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String upload(MultipartFile file);
}
