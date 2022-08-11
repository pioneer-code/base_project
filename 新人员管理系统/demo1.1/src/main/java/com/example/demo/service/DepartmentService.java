package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Mapper.CompanyMapper;
import com.example.demo.Mapper.DepartmentMapper;
import com.example.demo.bean.Company;
import com.example.demo.bean.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DepartmentService {
//    private DepartmentMapper departmentMapper=null;
//    public DepartmentService(){
//        InputStream is = null;
//        try {
//            is = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
//        SqlSession session = sessionFactory.openSession(true);
//        this.departmentMapper = session.getMapper(DepartmentMapper.class);
//    }

    /**
     * 查询一个部门记录
     * @param company_name
     * @param department_name
     * @return
     */
    public Map<String, Object> department_select_one(String company_name, String department_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company_name).eq("department_name",department_name);
        Department department = departmentMapper.selectOne(wrapper);
        map.put("department",department);
        return map;
    }

    public Map<String, Object> department_select(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company_name);
        List<Department> list = departmentMapper.selectList(wrapper);
        map.put("department",list);
        return map;
    }

    public Map<String, Object> department_update(Department department) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        UpdateWrapper<Department> wrapper = new UpdateWrapper<>();
        wrapper.set("plan_amount",department.getPlanAmount()).set("already_amount",department.getAlreadyAmount())
                .set("requirements",department.getRequirements()).eq("company_name",department.getCompanyName())
                .eq("department_name",department.getDepartmentName());
        int update = departmentMapper.update(null, wrapper);
        map.put("update",update);
        return map;
    }

    public Map<String, Object> department_delete_one(String company_name, String department_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company_name).eq("department_name",department_name);
        int delete = departmentMapper.delete(wrapper);
        map.put("delete",delete);
        return map;
    }

    public Map<String, Object> department_insert(Department department) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        int insert = departmentMapper.insert(department);
        map.put("insert",insert);
        return map;
    }
}
