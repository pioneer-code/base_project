package com.example.demo.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("staff")
public class Staff {
    private String idNumber;
    private String name;
    private String password;
    private String sex;
    private String telephone;
    private String email;
    private String nativePlace;
    private String educational;
    private String graduateSchool;
    private String company;
    private String departmentName;
    private Date entryTime;
    private int salary;
    private int employ;
    private String technique;
    private String evaluation;

    public Staff(String idNumber, String password) {
        this.idNumber = idNumber;
        this.password = password;
    }
}
