package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.pojo.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
public class EmployeeController {
    @GetMapping("/emps")
    public String list(Model model){
        EmployeeDao employeeDao = new EmployeeDao();
        model.addAttribute("emps", employeeDao.getAll());
        return "emp/list";
    }

    @GetMapping("/emp")
    public String emp(Model model){
        DepartmentDao departmentDao = new DepartmentDao();
        model.addAttribute("depts", departmentDao.getAll());
        return "emp/add";
    }

    @GetMapping("/emp/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Employee employee = new Employee();

        employee.setId(12);
        employee.setName("Tom");
        employee.setAge(23);
        employee.setBirth(new Date());
        employee.setSex(1);

        model.addAttribute("emp", employee);
        return "emp/add";
    }

    // SpringMVC会自动绑定数据
    @PostMapping("/emp")
    public String add(Employee  employee){
        System.out.println("add");
        System.out.println(employee);
        return "redirect:/emps";
    }

    @PutMapping("/emp")
    public String update(Employee employee){
        System.out.println("update");
        System.out.println(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete");
        System.out.println(id);
        return "redirect:/emps";
    }


}
