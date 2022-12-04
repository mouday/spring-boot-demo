package com.example.demo.mapper;

import com.example.demo.pojo.Department;
import org.apache.ibatis.annotations.*;


// 指定这是一个操作数据库的mapper
// @Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department getById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteById(Integer id);

    // 插入数据后自增主键自动设置到department
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(name) values(#{name}) ")
    public int insert(Department department);

    @Update("update department set name = #{name} where id = #{id} ")
    public int update(Department department);
}
