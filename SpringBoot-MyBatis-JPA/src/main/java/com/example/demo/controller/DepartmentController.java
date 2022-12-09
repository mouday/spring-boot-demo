package com.example.demo.controller;


import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentService.getById(id);
    }

    @GetMapping("/dept/update")
    public Department updateDepartment(Department department){
        return departmentService.update(department);
    }

    @GetMapping("/getEmp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeMapper.getById(id);
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department){
        departmentMapper.insert(department);
        return department;
    }


}
