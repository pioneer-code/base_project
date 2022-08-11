package com.example.demo.controller;

import com.example.demo.bean.Staff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping() //类路径访问地址
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService employeeService;

    @GetMapping("/employee/login")
    public Map<String, Object> login(@RequestParam("idnum") String id_number,
                        @RequestParam("password") String password) {
        return employeeService.login(id_number, password);
    }

    @PostMapping("/employee/register")
    public Map<String, Object> register(@RequestParam("id_number") String idNumber,
                                        @RequestParam("name") String name,
                                        @RequestParam("password") String password,
                                        @RequestParam("sex") String sex,
                                        @RequestParam("telephone") String telephone,
                                        @RequestParam("email") String email,
                                        @RequestParam("native_place") String nativePlace,
                                        @RequestParam("educational") String educational,
                                        @RequestParam("graduate_school") String graduateSchool,
                                        @RequestParam("company") String company,
                                        @RequestParam("department_name") String departmentName,
                                        @RequestParam("entry_time") Date entryTime,
                                        @RequestParam("salary") int salary,
                                        @RequestParam("employ") int employ,
                                        @RequestParam("technique") String technique,
                                        @RequestParam("evaluation") String evaluation) {
        Staff staff = new Staff(idNumber, name, password, sex, telephone, email, nativePlace, educational,
                graduateSchool, company, departmentName, entryTime, salary, employ, technique, evaluation);

        return employeeService.register(staff);
    }

    @PostMapping("/employee/register_new")
    public Map<String, Object> register_new(@RequestParam("id_number") String idNumber,
                                        @RequestParam("password") String password,
                                        @RequestParam("repassword") String repassword
                                        ) {

//        String idNumber="214";
        return employeeService.register_new(idNumber,password,repassword);
    }

    @GetMapping("/employee/select_infomation")
    public Map<String, Object> select_infomation(@RequestParam("id_number") String idNumber) {
        return employeeService.select_infomation(idNumber);
    }

    @PostMapping("/employee/update_evaluation")
    public Map<String, Object> update_evaluation(@RequestParam("id_number") String idNumber,
                                                 @RequestParam("evaluation") String evaluation) {

        return employeeService.update_evaluation(idNumber,evaluation);
    }

    @GetMapping("/employer/staff/select")
    public Map<String, Object> staff_select(@RequestParam("company_name") String company_name) {
        return employeeService.staff_select(company_name);
    }

    @GetMapping("/employer/staff/select_id_name")
    public Map<String, Object> select_id_name(@RequestParam("company_name") String company_name) {
        return employeeService.select_id_name(company_name);
    }

    @GetMapping("/employer/staff/select_one")
    public Map<String, Object> staff_select_one(@RequestParam("company_name") String company_name,
                                                @RequestParam("idNumber") String id_number) {
        return employeeService.staff_select_one(company_name,id_number);
    }

    @PostMapping("/employer/staff/update")
    public Map<String,Object> staff_update(@RequestParam("idNumber") String idNumber,
                                           @RequestParam("company") String company,
                                           @RequestParam("departmentName") String departmentName,
                                           @RequestParam("entryTime") Date entryTime,
                                           @RequestParam("salary") int salary,
                                           @RequestParam("employ") int employ,
                                           @RequestParam("technique") String technique,
                                           @RequestParam("evaluation") String evaluation){


        return employeeService.staff_update(idNumber,company, departmentName, entryTime, salary, employ, technique, evaluation);
    }
    @PostMapping("/employee/staff/update_infomation")
    public Map<String,Object> update_infomation(@RequestParam("idNumber") String idNumber,
                                           @RequestParam("name") String name,
                                           @RequestParam("sex") String sex,
                                           @RequestParam("telephone") String telephone,
                                           @RequestParam("email") String email,
                                           @RequestParam("nativePlace") String nativePlace,
                                           @RequestParam("educational") String educational,
                                           @RequestParam("graduateSchool") String graduateSchool,
                                           @RequestParam("company") String company,
                                           @RequestParam("departmentName") String departmentName,
                                           @RequestParam("entryTime") Date entryTime,
                                           @RequestParam("salary") int salary,
                                           @RequestParam("employ") int employ,
                                           @RequestParam("technique") String technique,
                                           @RequestParam("evaluation") String evaluation){

        Staff staff = new Staff(idNumber, name, "", sex, telephone, email, nativePlace, educational,
                graduateSchool, company, departmentName, entryTime, salary, employ, technique, evaluation);
        return employeeService.update_infomation(staff);
    }

    @PostMapping("/employer/staff/insert")
    public Map<String,Object> staff_insert(@RequestParam("idNumber") String idNumber,
                                           @RequestParam("name") String name,
                                           @RequestParam("password") String password,
                                           @RequestParam("sex") String sex,
                                           @RequestParam("telephone") String telephone,
                                           @RequestParam("email") String email,
                                           @RequestParam("nativePlace") String nativePlace,
                                           @RequestParam("educational") String educational,
                                           @RequestParam("graduateSchool") String graduateSchool,
                                           @RequestParam("company") String company,
                                           @RequestParam("departmentName") String departmentName,
                                           @RequestParam("entryTime") Date entryTime,
                                           @RequestParam("salary") int salary,
                                           @RequestParam("employ") int employ,
                                           @RequestParam("technique") String technique,
                                           @RequestParam("evaluation") String evaluation) {
        Staff staff = new Staff(idNumber, name, password, sex, telephone, email, nativePlace, educational,
                graduateSchool, company, departmentName, entryTime, salary, employ, technique, evaluation);
        return employeeService.register(staff);
    }

    @GetMapping("/employer/staff/delete")
    public Map<String,Object> staff_delete(@RequestParam("id_number") String id_number){
        return employeeService.staff_delete(id_number);
    }

    @GetMapping("/employer/staff/select_employ")
    public Map<String,Object> staff_select_employ(@RequestParam("company_name") String company_name,
                                                  @RequestParam("employ") int employ){
        return employeeService.staff_select_employ(company_name, employ);

    }

    @PostMapping("/employee/staff/update_password")
    public Map<String,Object> update_password(@RequestParam("id_number") String id_number,
                                              @RequestParam("old_password") String old_password,
                                              @RequestParam("new_password") String new_password,
                                              @RequestParam("re_password") String re_password
    ){

        return employeeService.update_password(id_number,old_password,new_password,re_password);
    }
}
