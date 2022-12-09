package com.example.demo.mapper;

import com.example.demo.pojo.Employee;

// @Mapper 或@MapperScan 将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getById(Integer id);

    public int deleteById(Integer id);

    public void insert(Employee employee);
}
