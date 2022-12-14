# SpringBoot-EasyExcel

SpringBoot使用EasyExcel实现Excel文件的导出下载和上传导入功能

文件目录

```
$ tree -I target
.
├── README.md
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── example
        │           └── demo
        │               ├── DemoApplication.java
        │               ├── controller
        │               │   └── EasyExcelController.java
        │               ├── converter
        │               │   └── GenderConverter.java
        │               └── excel
        │                   └── Member.java
        └── resources
            ├── application.properties
            └── json
                └── members.json

```

依赖 pom.xml

```xml

<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.11</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<properties>
    <java.version>1.8</java.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!--EasyExcel相关依赖-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>easyexcel</artifactId>
        <version>3.0.5</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.68</version>
    </dependency>

    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-json</artifactId>
        <version>5.8.10</version>
    </dependency>

</dependencies>

```

Member.java

```java
package com.example.demo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.demo.converter.GenderConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 购物会员
 * Created by macro on 2021/10/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Member {
    @ExcelProperty("ID")
    @ColumnWidth(10)
    private Long id;

    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;

    @ExcelIgnore
    private String password;

    @ExcelProperty("昵称")
    @ColumnWidth(20)
    private String nickname;

    @ExcelProperty("出生日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty("手机号")
    @ColumnWidth(20)
    private String phone;

    @ExcelIgnore
    private String icon;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    @ColumnWidth(10)
    private Integer gender;
}
```

GenderConverter.java

```java
package com.example.demo.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.StringUtils;

/**
 * excel性别转换器
 * Created by macro on 2021/12/29.
 */
public class GenderConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        //对象属性类型
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //CellData属性类型
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
        //CellData转对象属性
        String cellStr = context.getReadCellData().getStringValue();

        if (StringUtils.isEmpty(cellStr)) return null;

        if ("男".equals(cellStr)) {
            return 0;
        } else if ("女".equals(cellStr)) {
            return 1;
        } else {
            return null;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        //对象属性转CellData
        Integer cellValue = context.getValue();

        if (cellValue == null) {
            return new WriteCellData<>("");
        }

        if (cellValue == 0) {
            return new WriteCellData<>("男");
        } else if (cellValue == 1) {
            return new WriteCellData<>("女");
        } else {
            return new WriteCellData<>("");
        }
    }
}
```

EasyExcelController.java

```java
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
```

members.json

```json
[
  {
    "id": 1,
    "username": "刘备",
    "password": "123456",
    "nickname": "刘玄德",
    "birthday": "2000-10-10",
    "phone": "1234567890",
    "icon": "icon",
    "gender": "1"
  },
  {
    "id": 2,
    "username": "关于",
    "password": "234567",
    "nickname": "关云长",
    "birthday": "2000-10-10",
    "phone": "1234567890",
    "icon": "icon",
    "gender": "1"
  },
  {
    "id": 3,
    "username": "用户名",
    "password": "password",
    "nickname": "昵称",
    "birthday": "2000-10-10",
    "phone": "1234567890",
    "icon": "icon",
    "gender": "0"
  }
]
```

下载的文件 `会员列表.xlsx`

![](excel.png)

上传文件

```json
[
    {
        "id": 1,
        "username": "刘备",
        "password": null,
        "nickname": "刘玄德",
        "birthday": "2000-10-09T16:00:00.000+00:00",
        "phone": "1234567890",
        "icon": null,
        "gender": 1
    },
    {
        "id": 2,
        "username": "关于",
        "password": null,
        "nickname": "关云长",
        "birthday": "2000-10-09T16:00:00.000+00:00",
        "phone": "1234567890",
        "icon": null,
        "gender": 1
    },
    {
        "id": 3,
        "username": "用户名",
        "password": null,
        "nickname": "昵称",
        "birthday": "2000-10-09T16:00:00.000+00:00",
        "phone": "1234567890",
        "icon": null,
        "gender": 0
    }
]
```

>参考
[SpringBoot 实现 Excel 导入导出，性能爆表，用起来够优雅！](https://mp.weixin.qq.com/s/P017RfO8TDL5LxGzBoTLSA)
