package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.ViewMapper;
import com.example.demo.bean.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ViewServicce {


    public Map<String, Object> department_count(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ViewMapper viewMapper = session.getMapper(ViewMapper.class);
        List<LinkedHashMap> links = viewMapper.select_department_count(company_name); //获得数据

        Object[] department_name=new Object[links.size()]; //获取等长的数组
        Object[] count=new Object[links.size()]; //获取等长的数组
        int i =0;

        for (LinkedHashMap link : links) {
            department_name[i]=link.get("department_name");
            count[i]=link.get("count");
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        LinkedHashMap<Object,Object> data = new LinkedHashMap<>();

        data.put("department_name",department_name);
        data.put("count",count);

        map.put("res",data);
        return map;
    }

    public Map<String, Object> project_completion(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ViewMapper viewMapper = session.getMapper(ViewMapper.class);
        Map<String, Object> map = new HashMap<>();
        List<LinkedHashMap> links = viewMapper.project_completion(company_name);

        Object[] department_name=new Object[links.size()]; //获取等长的数组
        Object[] status=new Object[links.size()]; //获取等长的数组
        Object[] num=new Object[links.size()]; //获取等长的数组
        int i =0;

        for (LinkedHashMap link : links) {
            department_name[i]=link.get("department_name");
            status[i]=link.get("status");
            num[i]=link.get("num");
            i++;
        }
        Object []a=new Object[department_name.length];
        Object []b=new Object[department_name.length];
        for (int i1 = 0; i1 < department_name.length; i1++) {
            a[i1]=status[i1]+"";
            a[i1]=department_name[i1]+""+a[i1];
            b[i1]=num[i1];
        }
        LinkedHashMap<Object,Object> data = new LinkedHashMap<>();


        data.put("部门名和状态",a);
        data.put("num",b);

        map.put("res",data);
        return map;
    }

    public Map<String, Object> staff_sex(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ViewMapper viewMapper = session.getMapper(ViewMapper.class);


        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();

        List<LinkedHashMap> links = viewMapper.select_staff_sex(company_name);

        Object[] sex=new Object[links.size()]; //获取等长的数组
        Object[] num=new Object[links.size()]; //获取等长的数组
        int i =0;

        for (LinkedHashMap link : links) {
            sex[i]=link.get("sex");
            num[i]=link.get("num");
            i++;
        }
        LinkedHashMap<Object,Object> data = new LinkedHashMap<>();

        data.put("sex",sex);
        data.put("num",num);

        map.put("res",data);
        return map;
    }

    public Map<String, Object> educational_distribution(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ViewMapper viewMapper = session.getMapper(ViewMapper.class);



        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();

        List<LinkedHashMap> links = viewMapper.select_staff_education(company_name);
        Object[] educational=new Object[links.size()]; //获取等长的数组
        Object[] num=new Object[links.size()]; //获取等长的数组
        int i =0;

        for (LinkedHashMap link : links) {
            educational[i]=link.get("educational");
            num[i]=link.get("count");
            i++;
        }
        LinkedHashMap<Object,Object> data = new LinkedHashMap<>();

        data.put("educational",educational);
        data.put("num",num);

        map.put("res",data);
        return map;
    }

    public Map<String, Object> interview_people_count(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        ViewMapper viewMapper = session.getMapper(ViewMapper.class);
        List<LinkedHashMap> interview_status_people = viewMapper.interview_status_people(company_name);
        List<LinkedHashMap> trial_status_people = viewMapper.trial_status_people(company_name);

        Object[] status0=new Object[interview_status_people.size()]; //获取等长的数组
        Object[] status1=new Object[interview_status_people.size()]; //获取等长的数组

//        Object[] trial_status=new Object[trial_status_people.size()]; //获取等长的数组
//        Object[] trial_num=new Object[trial_status_people.size()]; //获取等长的数组
        Map<String, Object> map = new HashMap<>();
        map.put("阶段名字", new String[]{"面试期", "试用期"});

        int i=0;
        int j=0;
        System.out.println(interview_status_people);
        System.out.println(trial_status_people);
        status0[0]=interview_status_people.get(0).get("num");
        status0[1]=trial_status_people.get(0).get("num");

        status1[0]=interview_status_people.get(1).get("num");
        status1[1]=trial_status_people.get(1).get("num");

        map.put("状态是0",status0);
        map.put("状态是1",status1);

//
//        for (LinkedHashMap link : interview_status_people) {
//            status[i]=link.get("status");
//            num[i]=link.get("num");
//            i++;
//        }
//        for (LinkedHashMap link : trial_status_people) {
//            trial_status[j]=link.get("status");
//            trial_num[j]=link.get("num");
//            j++;
//        }
//        LinkedHashMap<Object,Object> data = new LinkedHashMap<>();
//
//        data.put("status",interview_status);
//        data.put("num",interview_num);
//        LinkedHashMap<Object,Object> data1 = new LinkedHashMap<>();
//
//        data1.put("status",trial_status);
//        data1.put("num",trial_num);
//
//
//
//        map.put("面试期间",data);
//        map.put("试用期",data1);
//
//        Map<String, Object> map1 = new HashMap<>();
//        map1.put("res",map);

        return map;
    }
}
