package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("project")
public class Project {
    private String idNumber;
    private int id;
    private String participant;
    private Date startTime;
    private int status;
    private String content;
    private String summary;
}
