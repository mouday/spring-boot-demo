package com.example.demo.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.utils.StringUtil;
import com.example.demo.vo.FileUploadVo;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult uploadFile(MultipartFile file) throws IOException {
        // file 是一个临时文件
        log.info("file: {}", file);

        String suffix = StringUtil.getFilenameSuffixWithDot(file.getOriginalFilename());

        // 使用uuid生成文件名，防止文件名重复造成文件覆盖
        String filename = UUID.randomUUID().toString() + suffix;

        // 转存文件
        file.transferTo(new File(this.getUploadDirectory(), filename));

        // 返回url
        FileUploadVo fileUploadVo = new FileUploadVo();
        fileUploadVo.setUrl("/upload/" + filename);

        return JsonResult.success(fileUploadVo);
    }

    /**
     * 获取文件保存路径
     * 参考：https://www.bbsmax.com/A/GBJrE67Wz0/
     *
     * @return File
     */
    public File getUploadDirectory() {
        // 获取当前运行目录
        File path = new File("");

        System.out.println("path:" + path.getAbsolutePath());

        File upload = new File(path.getAbsolutePath(), "public/upload");

        // 不存在则创建
        if (!upload.exists()) {
            upload.mkdirs();
        }

        return upload;
    }
}
