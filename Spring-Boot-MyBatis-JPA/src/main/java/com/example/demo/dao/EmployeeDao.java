package com.example.demo.dao;

import com.example.demo.pojo.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EmployeeDao {
    public List<Employee> getAll() {

        List<Employee> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setName("name" + i);
            employee.setAge(20 + i);

            double r = Math.random();
            if (r > 0.5) {
                employee.setSex(1);
            } else {
                employee.setSex(0);
            }

            employee.setBirth(new Date());
            list.add(employee);

        }
        return list;
    }
}
