package com.example.demo.controller;

import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.bean.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/employer/project") //类路径访问地址
@CrossOrigin

public class ProjectController {
    @Autowired
    ProjectService projectServicer;

    @GetMapping("/select/company")
    public Map<String,Object> select_company_name(@RequestParam("company_name") String company_name){
        return projectServicer.select_company_name(company_name);
    }

    @GetMapping("/select/department")
    public Map<String,Object> select_company_name(@RequestParam("company_name") String company_name,
                                                  @RequestParam("department_name") String department_name){
        if (StringUtils.isEmpty(department_name)){
            return projectServicer.select_company_name(company_name);

        }else {
            return projectServicer.select_company_name_department_name(company_name, department_name);
        }
    }

    @PostMapping("/insert")
    public Map<String,Object> insert_into(@RequestParam("id_number") String id_number,
                                          @RequestParam("participant") String participant,
                                          @RequestParam("start_time") Date start_time,
                                          @RequestParam("status") int status,
                                          @RequestParam("content") String content,
                                          @RequestParam("summary") String summary
                                                  ){
//        String participants=String.join("、",participant);
        if (participant.length()>0){
            participant=participant.substring(1);
        }
        Project project = new Project(id_number, 0, participant, start_time, status, content, summary);
        return projectServicer.insert_into(project);
    }

    @GetMapping("/delete")
    public Map<String,Object> delete_id(@RequestParam("id_number") String id_number,
                                        @RequestParam("id") int id){

        return projectServicer.delete_id(id_number,id);
    }
}
