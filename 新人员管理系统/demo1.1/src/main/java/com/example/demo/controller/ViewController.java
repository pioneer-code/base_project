package com.example.demo.controller;

import com.example.demo.service.ViewServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/view") //类路径访问地址
@CrossOrigin
public class ViewController {
    @Autowired
    ViewServicce viewServicce;

    /**
     * 查看公司各个部门的人数
     * @return
     */
    @GetMapping("/department/count")
    public Map<String,Object> department_count(@RequestParam("company_name") String company_name){
        return viewServicce.department_count(company_name);
    }

    /**
     * 公司员工的学历分布
     */
    @GetMapping("/staff/educational/distribution")
    public Map<String,Object> educational_distribution(@RequestParam("company_name") String company_name){
        return viewServicce.educational_distribution(company_name);
    }


    /**
     * 招聘流程各个阶段人数
     */
    @GetMapping("/interview/people/count")
    public Map<String,Object> interview_people_count(@RequestParam("company_name") String company_name){
        return viewServicce.interview_people_count(company_name);
    }

    /**
     * 查看公司各个部门项目完成情况
     * @return
     */
    @GetMapping("/project/completion")
    public Map<String,Object> project_completion(@RequestParam("company_name") String company_name){
        return viewServicce.project_completion(company_name);
    }

    /**
     * 查看公司性别分布
     * @return
     */
    @GetMapping("staff/sex")
    public Map<String,Object> staff_sex(@RequestParam("company_name") String company_name){
        return viewServicce.staff_sex(company_name);
    }
}
