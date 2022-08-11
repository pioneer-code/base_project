package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("trial")
public class Trial {
    private String idNumber;
    private String name;
    private String term;
    private Date startTime;
    private Date endTime;
    private String position;
    private String company;
    private String department;
    private int status;
    private String evaluation;
}
