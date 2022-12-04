package com.example.demo.dao;

import com.example.demo.pojo.Department;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();

        for(int i =0; i<5; i++){
            list.add(new Department(i, "name"+i));
        }
        return list;
    }
}
