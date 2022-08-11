package com.example.demo.controller;

import com.example.demo.bean.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/employer") //类路径访问地址
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("company_name") String company_name,
                                     @RequestParam("password") String password
                                     ){
        return companyService.login(company_name,password);
    }

    @PostMapping("/register")
    public Map<String,Object> register(@RequestParam("company_name") String idNumber,
                                       @RequestParam("password") String password,
                                       @RequestParam("boss_name") String boss_name,
                                       @RequestParam("address") String address,
                                       @RequestParam("phone") String phone){
        Company company = new Company(idNumber, password,boss_name,address,phone);

        return companyService.register(company);
    }

    @GetMapping("/select_company_name")
    public Map<String,Object> select_company(){
        return companyService.select_company();
    }

    @GetMapping("/select_company_name_and_id")
    public Map<String,Object> select_company_name_and_id(){
        return companyService.select_company_name_and_id();
    }

    @PostMapping("/update_password")
    public Map<String,Object> update_password(@RequestParam("company_name") String company_name,
                                                  @RequestParam("old_password") String old_password,
                                              @RequestParam("new_password") String new_password,
                                              @RequestParam("re_password") String re_password
                                              ){

        return companyService.update_password(company_name,old_password,new_password,re_password);
    }
}
