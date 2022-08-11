package com.example.demo.controller;

import com.example.demo.bean.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/employer/department") //类路径访问地址
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/select_one")
    public Map<String,Object> department_select_one(@RequestParam("company_name") String company_name,
                                                    @RequestParam("department_name") String department_name
    ){
        return departmentService.department_select_one(company_name,department_name);
    }

    @GetMapping("/select")
    public Map<String,Object> department_select(@RequestParam("company_name") String company_name){
        return departmentService.department_select(company_name);
    }

    @PostMapping("/update")
    public Map<String,Object> department_update(@RequestParam("companyName") String company_name,
                                                @RequestParam("departmentName") String department_name,
                                                @RequestParam("planAmount") int plan_amount,
                                                @RequestParam("alreadyAmount") int already_amount,
                                                @RequestParam("requirements") String requirements
    ){
        Department department = new Department(company_name, department_name, plan_amount, already_amount, requirements);
        return departmentService.department_update(department);
    }

    @GetMapping("/delete")
    public Map<String,Object> department_delete_one(@RequestParam("company_name") String company_name,
                                                    @RequestParam("department_name") String department_name
    ){
        return departmentService.department_delete_one(company_name,department_name);
    }

    @PostMapping("/insert")
    public Map<String,Object> department_insert(@RequestParam("companyName") String company_name,
                                                @RequestParam("departmentName") String department_name,
                                                @RequestParam("planAmount") int plan_amount,
                                                @RequestParam("alreadyAmount") int already_amount,
                                                @RequestParam("requirements") String requirements
    ){
        Department department = new Department(company_name, department_name, plan_amount, already_amount, requirements);
        return departmentService.department_insert(department);
    }
}
