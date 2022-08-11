package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Mapper.InterviewMapper;
import com.example.demo.Mapper.TrialMapper;
import com.example.demo.bean.Interview;
import com.example.demo.bean.Trial;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterviewService {
    public Map<String, Object> select(String company) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        InterviewMapper interviewMapper = session.getMapper(InterviewMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Interview> wrapper = new QueryWrapper<>();
        wrapper.eq("company",company);
        List<Interview> interview = interviewMapper.selectList(wrapper);
        map.put("interview",interview);

        return map;
    }

    public Map<String, Object> select_status(int status) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        InterviewMapper interviewMapper = session.getMapper(InterviewMapper.class);


        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Interview> wrapper = new QueryWrapper<>();
        wrapper.eq("status",status);
        List<Interview> interview = interviewMapper.selectList(wrapper);
        map.put("interview",interview);
        return map;
    }

    public Map<String, Object> turn(String company, String id_number) {

        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        InterviewMapper interviewMapper = session.getMapper(InterviewMapper.class);
        Map<String, Object> map = new HashMap<>();
        UpdateWrapper<Interview> wrapper= new UpdateWrapper<>();
        wrapper.eq("company",company).eq("id_number",id_number).set("status",1);
        int update = interviewMapper.update(null, wrapper);
        map.put("update",update);
        return map;
    }
}
