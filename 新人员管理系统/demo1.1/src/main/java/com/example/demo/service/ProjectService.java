package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.DepartmentMapper;
import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.Mapper.RewardPunishMapper;
import com.example.demo.Mapper.StaffMapper;
import com.example.demo.bean.Project;
import com.example.demo.bean.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ProjectService {
//    private ProjectMapper projectMapper=null;
//    private StaffMapper staffMapper=null;
//    public ProjectService(){
//        InputStream is = null;
//        try {
//            is = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
//        SqlSession session = sessionFactory.openSession(true);
//        this.projectMapper = session.getMapper(ProjectMapper.class);
//        this.staffMapper = session.getMapper(StaffMapper.class);
//    }

    public Map<String, Object> select_company_name(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        List<LinkedHashMap> list = projectMapper.select_company_name(company_name);
        map.put("project",list);
        return map;
    }

    public Map<String, Object> select_company_name_department_name(String company_name, String department_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        List<LinkedHashMap> list = projectMapper.select_company_name_department_name(company_name, department_name);
        map.put("project",list);
        return map;
    }

    public Map<String, Object> insert_into(Project project) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        int insert=0;
        try {
            projectMapper.insert_into(project);
            insert=1;
        }catch (Exception e){
            insert=0;
        }
        map.put("insert",insert);
        return map;
    }

    public Map<String, Object> delete_id(String id_number, int id) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number).eq("id",id);
        int delete=0;
        try {
            projectMapper.delete(wrapper);
            delete=1;
        }catch (Exception e){
            System.out.println(e);
        }
        map.put("delete",delete);
        return map;

    }
}
