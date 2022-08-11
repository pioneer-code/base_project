package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface ViewMapper {
    @Select("select department_name,count(*) `count` from staff where company=#{company_name} group by department_name")
    List<LinkedHashMap> select_department_count(String company_name);

    @Select("SELECT sex,COUNT(*) as num FROM staff WHERE company=#{company_name} GROUP BY sex  HAVING sex IS NOT NULL ;")
    List<LinkedHashMap> select_staff_sex(String company_name);

    @Select("SELECT educational,COUNT(*) `count` FROM staff WHERE company=#{company_name} GROUP BY educational  HAVING educational IS NOT NULL ;")
    List<LinkedHashMap> select_staff_education(String company_name);

    @Select("SELECT department_name,`status`,COUNT(*) as num " +
            "FROM (SELECT p.id_number,s.department_name,p.status FROM project p LEFT JOIN staff s USING(id_number) " +
            "WHERE company=#{company_name}) AS a " +
            "GROUP BY department_name ,`status` " +
            "HAVING department_name IS NOT NULL;")
    List<LinkedHashMap> project_completion(String company_name);

    @Select("SELECT `status`,COUNT(*) as num FROM interview WHERE company=#{company_name} GROUP BY `status`")
    List<LinkedHashMap> interview_status_people(String company_name);


    @Select("SELECT `status`,COUNT(*) as num FROM trial WHERE company=#{company_name} GROUP BY STATUS")
    List<LinkedHashMap> trial_status_people(String company_name);

}
