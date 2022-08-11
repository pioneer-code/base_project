package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.Company;
import com.example.demo.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
