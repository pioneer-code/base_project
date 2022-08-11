package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("interview")
public class Interview {
    private String idNumber;
    private String name;
    private String graduateSchool;
    private String educational;
    private String major;
    private String intention;
    private String company;
    private String department;
    private int status;
    private String evaluation;
}
