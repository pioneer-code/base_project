package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.Mapper.RewardPunishMapper;
import com.example.demo.Mapper.StaffMapper;
import com.example.demo.bean.RewardPunish;
import com.example.demo.bean.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class RewardPunishService {

    public Map<String, Object> company_name_sign(String company_name, String sign) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);

        List<LinkedHashMap> link = rewardPunishMapper.company_name_sign(company_name, sign);

        HashMap<String, Object> map = new HashMap<>();
        map.put("reward_punish",link);
        return map;
    }

    public Map<String, Object> update_id_number_id(RewardPunish punish) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        UpdateWrapper<RewardPunish> wrapper = new UpdateWrapper<>();
        wrapper.set("reason",punish.getReason()).set("sign",punish.getSign()).set("time",punish.getTime()).eq("id_number",punish.getIdNumber());
        int update = rewardPunishMapper.update(null, wrapper);
        map.put("update",update);
        return map;
    }

    public Map<String, Object> delete_id_number_id(String id_number, int id) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<RewardPunish> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number).eq("id",id);
        int delete = rewardPunishMapper.delete(wrapper);
        map.put("delete",delete);
        return map;
    }

    public Map<String, Object> insert_into(RewardPunish punish) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        int insert=0;
        try {
            rewardPunishMapper.insert_into(punish);
            insert=1;
        }catch (Exception e){
            insert=0;
        }
        map.put("insert",insert);
       return map;
    }

    public Map<String, Object> select_one(String id_number, int id) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<RewardPunish> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number).eq("id",id);
        try {
            List<RewardPunish> lis = rewardPunishMapper.selectList(wrapper);
            map.put("reward_punish",lis);
        }catch (Exception e){
            map.put("reward_punish",null);
        }
        return map;
    }

    public Map<String, Object> company_name_all(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);

        List<LinkedHashMap> link = rewardPunishMapper.company_name_all(company_name);

        HashMap<String, Object> map = new HashMap<>();
        map.put("reward_punish",link);
        return map;
    }

    public Map<String, Object> select_staff(String id_number) {

        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        RewardPunishMapper rewardPunishMapper = session.getMapper(RewardPunishMapper.class);

        QueryWrapper<RewardPunish> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number);
        List<RewardPunish> datas = rewardPunishMapper.selectList(wrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("datas",datas);
        return map;
    }
}
