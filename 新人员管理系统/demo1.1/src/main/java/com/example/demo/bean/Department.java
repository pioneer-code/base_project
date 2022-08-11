package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("department")
public class Department {
    private String companyName;
    private String departmentName;
    private int planAmount;
    private int alreadyAmount;
    private String requirements;
}
