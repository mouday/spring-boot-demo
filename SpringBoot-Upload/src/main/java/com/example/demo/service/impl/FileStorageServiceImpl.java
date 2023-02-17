package com.example.demo.service.impl;

import com.example.demo.config.FileStorageConfig;
import com.example.demo.service.FileStorageService;
import com.example.demo.utils.StringUtil;
import com.example.demo.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Override
    public String upload(MultipartFile file) {
        String suffixWithDot = StringUtil.getFilenameSuffixWithDot(file.getOriginalFilename());

        String filename = StringUtil.getRandomFilename(suffixWithDot);

        String uploadDirectory = fileStorageConfig.getUploadDirectory();

        // 转存文件
        String url = null;
        try {
            file.transferTo(new File(uploadDirectory, filename));
            url = UrlUtil.urlJoin(fileStorageConfig.getDomain(), fileStorageConfig.getBaseUrl(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}
