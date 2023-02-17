package com.example.demo.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.service.FileStorageService;
import com.example.demo.utils.StringUtil;
import com.example.demo.vo.FileUploadVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 文件上传 页面
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    /**
     * 文件上传 接口
     *
     * @param file
     * @return
     * @url http://127.0.0.1:8080/backend/page/common/upload.html
     */
    @PostMapping("/api/upload")
    @ResponseBody
    public JsonResult uploadFile(MultipartFile file) {
        // file 是一个临时文件
        log.info("file: {}", file);

        String fileUrl = fileStorageService.upload(file);

        // 返回url
        FileUploadVo fileUploadVo = new FileUploadVo();
        fileUploadVo.setUrl(fileUrl);

        return JsonResult.success(fileUploadVo);
    }
}
