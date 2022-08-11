package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("company")
public class Company {
    private String companyName;
    private String password;
    private String bossName;
    private String address;
    private String phone;
}