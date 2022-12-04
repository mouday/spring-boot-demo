package com.mouday.demo.controller;

import com.mouday.demo.entity.Result;
import com.mouday.demo.entity.es.ESBlog;
import com.mouday.demo.entity.mysql.MySQLBlog;
import com.mouday.demo.repository.es.EsBlogRepository;
import com.mouday.demo.repository.mysql.MySQLBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class BlogController {
    @Autowired
    MySQLBlogRepository mySQLBlogRepository;

    @Autowired
    EsBlogRepository esBlogRepository;

    @GetMapping("/api/mysqlblogs")
    public Object mysqlList(){
        List<MySQLBlog> list = mySQLBlogRepository.queryAll();
        return Result.success(list);
    }

    /**
     * es 搜索
     */
    @GetMapping("/api/esblogs")
    public Object esList(){
        Iterable<ESBlog> iterable = esBlogRepository.findAll();
        Iterator<ESBlog> iterator = iterable.iterator();
        ESBlog blog = iterator.next();
        return Result.success(blog);
    }

    /**
     * Mysql 搜索
     */
    @GetMapping("/api/blog/{id}")
    public Object detail(@PathVariable Integer id){
        Optional<MySQLBlog> optional = mySQLBlogRepository.findById(id);
        if(optional.isPresent()){
            return Result.success(optional.get());
        } else{
            return Result.success(null);
        }
    }

    @PostMapping("/api/search")
    public Object search(@RequestBody Param param){
        String type = param.getType();

        StopWatch watch = new StopWatch();
        watch.start();

        Map<String, Object> data = new HashMap<>();

        if("es".equalsIgnoreCase(type)){
        //    es

        //    {
        //   "query": {
        //     "bool": {
        //       "should": [
        //         {
        //           "match_phrase": {
        //             "title": "杏花"
        //           }
        //         },
        //         {
        //           "match_phrase": {
        //             "content": "杏花"
        //           }
        //         }
        //       ]
        //     }
        //   }
        // }

            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title", param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content", param.getKeyword()));
            String query = builder.toString();
            System.out.println(query);

            Page<ESBlog> page = (Page<ESBlog>)esBlogRepository.search(builder);
            List<ESBlog> list = page.getContent();
            data.put("list", list);

        } else{
        //    mysql
            List<MySQLBlog> list = mySQLBlogRepository.queryBlog(param.getKeyword());
            data.put("list", list);
        }
        watch.stop();
        long totalTime = watch.getTotalTimeMillis();
        data.put("duration", totalTime);

        return Result.success(data);


    }

    @Data
    public static class Param{
        private String type;
        private String keyword;
    }
}
