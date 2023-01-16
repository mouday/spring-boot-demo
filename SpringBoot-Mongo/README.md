# Java：SpringBoot项目中MongoTemplate的新增、删除、更新、查询操作

@[TOC](目录)

项目结构
```bash
$ tree -I target
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── demo
    │   │               ├── Application.java
    │   │               ├── config
    │   │               │   └── MongoConfiguration.java
    │   │               └── entity
    │   │                   └── UserData.java
    │   └── resources
    │       └── application.yml
    └── test
        └── java
            └── com
                └── example
                    └── demo
                        └── MongoTemplateTest.java

```
## 引入mongodb依赖

依赖

```xml
<!--mongodb-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

完整pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.7</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <name>demo</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--mongodb-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
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
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

配置mongodb连接参数

application.yml

```yml
spring:
  data:
    mongodb:
       uri: 'mongodb://127.0.0.1:27017/data'

```

## 插入数据-默认

定义实体类

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "user")
public class UserData {

    private String name;

    private Integer age;

    private Date birthday;
}

```

插入数据

```java
package com.example.demo;

import com.example.demo.entity.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert(){
        UserData userData = new UserData();
        userData.setAge(20);
        userData.setName("Tom");
        userData.setBirthday(new Date(2000, 10, 01));

        mongoTemplate.insert(userData);

    }

}

```

查询数据

```js
db.getCollection('user').find({})
```

```json
{
    "_id" : ObjectId("63c4ff1625a0156daca425dc"),
    "name" : "Tom",
    "age" : 20,
    "birthday" : Date(60931152000000),
    "_class" : "com.example.demo.entity.UserData"
}
```

## 插入数据-自定义

默认插入方式，发现存在几个问题：

1. 多了一个`_class`字段，通过自定义配置类解决
2. 时间字段也不是我想要的，直接存字符串
3. `_id` 是ObjectId对象，直接自定义存一个uuid字符串

定义配置类

```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import javax.annotation.Resource;


@Configuration
public class MongoConfiguration {

    @Resource
    private MongoDatabaseFactorySupport mongoDatabaseFactorySupport;

    @Resource
    private MappingMongoConverter mappingMongoConverter;

    @Bean
    public MongoTemplate mongoTemplate() {

        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactorySupport, mappingMongoConverter);
        return mongoTemplate;
    }
}
```

修改实体类

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "user")
public class UserData {

    @Field("_id")
    private String docId;

    private String name;

    private Integer age;

    // private Date birthday;
    private String birthday;
}

```

插入数据
```java
package com.example.demo;

import com.example.demo.entity.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert() {
        UserData userData = new UserData();

        userData.setDocId(UUID.randomUUID().toString());
        userData.setAge(20);
        userData.setName("Tom");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date birthday = new Date(2000 - 1900, 10 - 1, 1);
        userData.setBirthday(simpleDateFormat.format(birthday));

        mongoTemplate.insert(userData);

    }

}
```

查看插入的数据

```json
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac20",
    "name" : "Tom",
    "age" : 20,
    "birthday" : "2000-10-01 00:00:00"
}
```

## 根据ID获取数据

```json
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac20",
    "name" : "Tom",
    "age" : 20,
    "birthday" : "2000-10-01 00:00:00"
}
```

```java
// select * from user where id = ? limit 1
String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac20";

UserData userData = mongoTemplate.findById(docId, UserData.class);
System.out.println(userData);
// UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac20, name=Tom, age=20, birthday=2000-10-01 00:00:00)
```

## 按照ID移除

```java
// delete from user where id = ?
String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac20";
Query query = new Query(Criteria.where("_id").is(docId));

DeleteResult deleteResult = mongoTemplate.remove(query, UserData.class);

System.out.println(deleteResult.getDeletedCount());
// 1
```

## 查询-条件-排序-分页

```java
// select * from user
// where age > 18
// order by age desc
// limit 1 offset 0

Query query = new Query(Criteria.where("age").gt(18));
query.with(Sort.by(Sort.Direction.DESC, "age"));
query.skip(0).limit(3);

// 查询数量
long count = mongoTemplate.count(query, UserData.class);

System.out.println(count);
// 2

// 查询列表
List<UserData> userDataList = mongoTemplate.find(query, UserData.class);

for (UserData userData : userDataList) {
    System.out.println(userData);
}

// UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac21, name=Jack, age=21, birthday=2000-10-02 00:00:00)
// UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac20, name=Tom, age=20, birthday=2000-10-01 00:00:00)
```

## 更新部分字段

```java
String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac21";
Query query = new Query(Criteria.where("_id").is(docId));

Update update = new Update();

SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
update.set("birthday", simpleDateFormat.format(new Date()));

UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserData.class);
System.out.println(updateResult.getModifiedCount());
// 1
```

```json
// 更新前
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac21",
    "name" : "Jack",
    "age" : 21,
    "birthday" : "2000-10-02 00:00:00"
}

// 更新后
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac21",
    "name" : "Jack",
    "age" : 21,
    "birthday" : "2023-01-16 17:07:23"
}
```

## 全文档更新

```java
String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac21";
Query query = new Query(Criteria.where("_id").is(docId));

UserData userData = new UserData();
userData.setDocId(docId);
userData.setName("jack");

Document document = (Document)mongoTemplate.getConverter().convertToMongoType(userData);

Update update = Update.fromDocument(document);

UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserData.class);
System.out.println(updateResult.getModifiedCount());
// 1
```

```json
// 更新前
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac21",
    "name" : "Jack",
    "age" : 21,
    "birthday" : "2023-01-16 17:07:23"
}

// 更新后
{
    "_id" : "08f23d0b-76ff-4d62-ac52-4fc68ceeac21",
    "name" : "jack"
}
```

## 完整代码
```java
package com.example.demo;

import com.example.demo.entity.UserData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入数据
     */
    @Test
    public void testInsert() {
        UserData userData = new UserData();

        userData.setDocId(UUID.randomUUID().toString());
        userData.setAge(20);
        userData.setName("Tom");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date birthday = new Date(2000 - 1900, 10 - 1, 1);
        userData.setBirthday(simpleDateFormat.format(birthday));

        mongoTemplate.insert(userData);

    }

    /**
     * 按id查询
     */
    @Test
    public void testFindById() {
        // select * from user where id = ? limit 1
        String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac20";

        UserData userData = mongoTemplate.findById(docId, UserData.class);
        System.out.println(userData);
        // UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac20, name=Tom, age=20, birthday=2000-10-01 00:00:00)
    }

    /**
     * 按照id移除
     */
    @Test
    public void testRemove() {
        // delete from user where id = ?
        String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac20";
        Query query = new Query(Criteria.where("_id").is(docId));

        DeleteResult deleteResult = mongoTemplate.remove(query, UserData.class);

        System.out.println(deleteResult.getDeletedCount());
        // 1
    }

    /**
     * 查询-条件-排序-分页
     */
    @Test
    public void testFind() {

        // select * from user
        // where age > 18
        // order by age desc
        // limit 1 offset 0

        Query query = new Query(Criteria.where("age").gt(18));
        query.with(Sort.by(Sort.Direction.DESC, "age"));
        query.skip(0).limit(3);

        // 查询数量
        long count = mongoTemplate.count(query, UserData.class);

        System.out.println(count);
        // 2

        // 查询列表
        List<UserData> userDataList = mongoTemplate.find(query, UserData.class);

        for (UserData userData : userDataList) {
            System.out.println(userData);
        }

        // UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac21, name=Jack, age=21, birthday=2000-10-02 00:00:00)
        // UserData(docId=08f23d0b-76ff-4d62-ac52-4fc68ceeac20, name=Tom, age=20, birthday=2000-10-01 00:00:00)
    }

    /**
     * 更新部分字段
     */
    @Test
    public void testUpdate() {
        String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac21";
        Query query = new Query(Criteria.where("_id").is(docId));

        Update update = new Update();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        update.set("birthday", simpleDateFormat.format(new Date()));

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserData.class);
        System.out.println(updateResult.getModifiedCount());
        // 1
    }

    /**
     * 全文档更新
     */
    @Test
    public void testUpdateDocument() {
        String docId = "08f23d0b-76ff-4d62-ac52-4fc68ceeac21";
        Query query = new Query(Criteria.where("_id").is(docId));

        UserData userData = new UserData();
        userData.setDocId(docId);
        userData.setName("jack");

        Document document = (Document)mongoTemplate.getConverter().convertToMongoType(userData);

        Update update = Update.fromDocument(document);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserData.class);
        System.out.println(updateResult.getModifiedCount());
        // 1
    }
}

```

> 参考
> [SpringBoot V2.7.5整合MongoDB V6.0.0图文详解](https://blog.csdn.net/weixin_43454365/article/details/127949574)
