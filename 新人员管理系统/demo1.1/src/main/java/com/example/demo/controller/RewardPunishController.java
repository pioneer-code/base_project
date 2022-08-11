package com.example.demo.controller;

import com.example.demo.bean.RewardPunish;
import com.example.demo.service.RewardPunishService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController      // Restful 风格返回json数据
@RequestMapping("/employer/reward_punish") //类路径访问地址
@CrossOrigin

public class RewardPunishController {

    @Autowired
    RewardPunishService rewardPunishService;

    @GetMapping("/select")
    public Map<String ,Object> company_name_sign(@RequestParam("company_name") String company_name,
                                                 @RequestParam("sign") String sign){ //0是惩罚、1是奖励
        return rewardPunishService.company_name_sign(company_name,sign);
    }
    @GetMapping("/select_all")
    public Map<String ,Object> company_name_all(@RequestParam("company_name") String company_name){
        return rewardPunishService.company_name_all(company_name);
    }

    @GetMapping("/select_staff")
    public Map<String ,Object> select_staff(@RequestParam("id_number") String id_number
                                                ){
        return rewardPunishService.select_staff(id_number);
    }

    @GetMapping("/select_one")
    public Map<String,Object> select_one(@RequestParam("id_number") String id_number,
                                         @RequestParam("id") int id){
        return rewardPunishService.select_one(id_number,id);
    }

    @PostMapping("/update")
    public Map<String ,Object> update_id_number_id(@RequestParam("id_number") String id_number,
                                                   @RequestParam("id") int id,
                                                   @RequestParam("sign") int sign,
                                                   @RequestParam("time") Date time,
                                                   @RequestParam("reason") String reason){
        RewardPunish punish = new RewardPunish(id_number, id, sign, time, reason);
        return rewardPunishService.update_id_number_id(punish);
    }

    @GetMapping("/delete")
    public Map<String ,Object> delete_id_number_id(@RequestParam("id_number") String id_number,
                                                   @RequestParam("id") int id){
        return rewardPunishService.delete_id_number_id(id_number,id);
    }

    @PostMapping("/insert")
    public Map<String ,Object> insert_into(@RequestParam("id_number") String id_number,
                                                   @RequestParam("sign") int sign,
                                                   @RequestParam("time") Date time,
                                                   @RequestParam("reason") String reason) {
        RewardPunish punish = new RewardPunish(id_number, 0, sign, time, reason);
        return rewardPunishService.insert_into(punish);
    }
}
