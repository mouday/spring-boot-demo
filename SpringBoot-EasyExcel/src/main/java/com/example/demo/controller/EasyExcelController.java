package com.example.demo.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.excel.Member;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * EasyExcel导入导出测试Controller
 * Created by macro on 2021/10/12.
 */
@Controller
@RequestMapping("/easyExcel")
public class EasyExcelController {

    /**
     * 下载地址：http://localhost:8080/easyExcel/exportMemberList
     *
     * @param response
     */
    @SneakyThrows(IOException.class)
    @RequestMapping(value = "/exportMemberList", method = RequestMethod.GET)
    public void exportMemberList(HttpServletResponse response) {
        this.setExcelResponseHeader(response, "会员列表");

        // List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);

        // https://blog.csdn.net/qq6420529/article/details/125215520
        ClassPathResource classPathResource = new ClassPathResource("/json/members.json");

        JSONArray objects = JSONUtil.readJSONArray(classPathResource.getFile(), Charset.forName("utf-8"));
        List<Member> memberList = objects.toList(Member.class);

        EasyExcel.write(response.getOutputStream())
                .head(Member.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("会员列表")
                .doWrite(memberList);
    }


    /**
     * 从Excel导入会员列表
     * <p>
     * http://localhost:8080/easyExcel/importMemberList
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @RequestMapping(value = "/importMemberList", method = RequestMethod.POST)
    @ResponseBody
    public List<Member> importMemberList(@RequestPart("file") MultipartFile file) {
        List<Member> memberList = EasyExcel.read(file.getInputStream())
                .head(Member.class)
                .sheet()
                .doReadSync();
        return memberList;
    }


    /**
     * 设置excel下载响应头属性
     */
    private void setExcelResponseHeader(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }
}