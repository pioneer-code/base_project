package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
//@Repository
public interface CompanyMapper extends BaseMapper<Company> {
    @Select("select company_name `value`,company_name label from company; ")
    List<LinkedHashMap> select_company_name_and_id();
}
