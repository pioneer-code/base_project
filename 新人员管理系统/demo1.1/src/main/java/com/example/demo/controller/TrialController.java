package com.example.demo.controller;

import com.example.demo.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/trial") //类路径访问地址
@CrossOrigin
public class TrialController {

    @Autowired
    TrialService trialService;

    @GetMapping("/select")
    public Map<String,Object> select(@RequestParam("company") String company){
        return trialService.select(company);
    }
    @GetMapping("/turn")
    public Map<String,Object> turn(@RequestParam("company") String company,
                                     @RequestParam("id_number") String id_number){
        return trialService.turn(company,id_number);
    }
}
