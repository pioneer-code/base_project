package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Mapper.TrialMapper;
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
public class TrialService {

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
        TrialMapper trialMapper = session.getMapper(TrialMapper.class);
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Trial> wrapper = new QueryWrapper<>();
        wrapper.eq("company",company);
        List<Trial> trials = trialMapper.selectList(wrapper);
        map.put("trials",trials);
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
        TrialMapper trialMapper = session.getMapper(TrialMapper.class);
        Map<String, Object> map = new HashMap<>();
        UpdateWrapper<Trial> wrapper= new UpdateWrapper<>();
        wrapper.eq("company",company).eq("id_number",id_number).set("status",1);
        int update = trialMapper.update(null, wrapper);
        map.put("update",update);
        return map;
    }
}
