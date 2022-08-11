package com.example.demo.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("reward_punish")
public class RewardPunish {
    private String idNumber;
    private int id;
    private int sign;
    private Date time;
    private String reason;
}
