package com.example.demo;

import com.example.demo.entity.UserData;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
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
public class MongoTemplateTest {

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

        PageRequest pageRequest = PageRequest.of(1, 20, Sort.by(Sort.Order.desc("date")));

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
