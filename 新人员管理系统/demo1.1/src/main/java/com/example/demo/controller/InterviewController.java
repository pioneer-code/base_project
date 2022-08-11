package com.example.demo.controller;

import com.example.demo.service.InterviewService;
import com.example.demo.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/interview") //类路径访问地址
@CrossOrigin
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @GetMapping("/select")
    public Map<String ,Object> select(@RequestParam("company") String company){
        return interviewService.select(company);
    }
    @GetMapping("/select/status")
    public Map<String ,Object> select_status(@RequestParam("status") int status) {
        return interviewService.select_status(status);
    }
    @GetMapping("/turn")
    public Map<String ,Object> turn(@RequestParam("company") String company,
                                             @RequestParam("id_number") String id_number) {
        return interviewService.turn(company,id_number);
    }
}
