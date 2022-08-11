package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

    @Select("select id_number value,name label from staff where company=#{company_name};")
    List<LinkedHashMap> select_id_name(String company_name);
}
