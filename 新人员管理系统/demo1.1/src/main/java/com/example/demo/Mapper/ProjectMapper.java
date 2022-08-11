package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    @Insert("insert into project(id_number,participant,start_time,status,content,summary) values(#{idNumber},#{participant},#{startTime}" +
            ",#{status},#{content},#{summary})")
    void insert_into(Project project);

    @Select("SELECT p.id_number,p.id,s.name,s.department_name as department,p.participant,p.start_time,p.`status`,p.content,p.summary " +
            "FROM project AS p LEFT JOIN staff AS s USING(id_number) WHERE s.company=#{company_name};")
    List<LinkedHashMap> select_company_name(String company_name);

    @Select("SELECT p.id_number,p.id,s.name,s.department_name AS department,p.participant,p.start_time,p.`status`,p.content,p.summary FROM project AS p LEFT JOIN staff AS s USING(id_number)\n" +
            " WHERE s.company=#{company_name} AND s.department_name =#{department_name};")
    List<LinkedHashMap> select_company_name_department_name(String company_name, String department_name);
}
