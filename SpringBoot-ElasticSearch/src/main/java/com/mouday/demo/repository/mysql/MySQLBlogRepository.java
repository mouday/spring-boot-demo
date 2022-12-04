package com.mouday.demo.repository.mysql;

import com.mouday.demo.entity.mysql.MySQLBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MySQLBlogRepository extends JpaRepository<MySQLBlog, Integer> {
    @Query("select e from MySQLBlog e order by e.createTime desc")
    List<MySQLBlog> queryAll();

    @Query("select e from MySQLBlog e where e.title like concat('%', :keyword, '%') " +
            "or e.content like concat('%', :keyword, '%')")
    List<MySQLBlog> queryBlog(@Param("keyword") String keyword);
}
