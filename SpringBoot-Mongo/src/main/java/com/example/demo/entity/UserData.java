package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class UserData {

    @Id
    private String docId;

    private String name;

    private Integer age;

    // private Date birthday;
    private String birthday;
}
