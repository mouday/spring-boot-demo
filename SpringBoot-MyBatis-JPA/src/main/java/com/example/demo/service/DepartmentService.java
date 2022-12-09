package com.example.demo.service;

import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = {"department"})
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    // 缓存数据
    @Cacheable(key = "#id")
    public Department getById(Integer id) {
        return departmentMapper.getById(id);
    }

    // 更新缓存
    @CachePut(key = "#department.id")
    public Department update(Department department) {
        departmentMapper.update(department);
        return department;
    }

    // 删除缓存
    @CacheEvict(allEntries = true)
    public String deleteAllCache() {
        return "success";
    }
}
