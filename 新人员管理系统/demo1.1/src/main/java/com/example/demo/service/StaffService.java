package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Mapper.CompanyMapper;
import com.example.demo.Mapper.RewardPunishMapper;
import com.example.demo.Mapper.StaffMapper;
import com.example.demo.bean.Company;
import com.example.demo.bean.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.*;

@Service
public class StaffService {
//    private StaffMapper staffMapper=null;
//    private RewardPunishMapper rewardPunishMapper=null;
//    public StaffService(){
//        InputStream is = null;
//        try {
//            is = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
//        SqlSession session = sessionFactory.openSession(true);
//        this.rewardPunishMapper = session.getMapper(RewardPunishMapper.class);
//        this.staffMapper = session.getMapper(StaffMapper.class);
//    }

    /**
     * 登录
     * @param id_number
     * @param password
     * @return
     */
    public Map<String, Object> login(String id_number, String password){
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

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number).eq("password",password);
        Staff satff_one = staffMapper.selectOne(wrapper);//查询id
        String msg="";
        if (StringUtils.isEmpty(satff_one)|| !satff_one.getPassword().equals(password)){
            msg="用户名或者密码错误";
        }else {
            msg= "登录成功";
            map.put("token",uuid);
        }

        map.put("msg",msg);
        return map;
    }

    /**
     * 注册
     * @param staff
     * @return
     */
    public Map<String , Object> register(Staff staff){
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

        String msg="";
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",staff.getIdNumber());
        Staff satff_one = staffMapper.selectOne(wrapper);//查询id
        if (StringUtils.isEmpty(satff_one)){ //公司不存在
            staffMapper.insert(staff);
            msg="注册成功";

        }else {
            msg="注册失败,账户已经存在";
        }
        map.put("msg",msg);
        map.put("staff",staff);
        return map;
    }

    /**
     * 查看个人简历
     * @param idNumber
     * @return
     */
    public Map<String, Object> select_infomation(String idNumber) {
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
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",idNumber);
        Staff staff = staffMapper.selectOne(wrapper);
        map.put("staff",staff);
        return map;
    }

    /**
     * 更新评价
     * @param idNumber
     * @param evaluation
     * @return
     */
    public Map<String, Object> update_evaluation(String idNumber,String evaluation) {
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
        UpdateWrapper<Staff> wrapper = new UpdateWrapper<>();
        wrapper.set("evaluation",evaluation).eq("id_number",idNumber);
        int update = staffMapper.update(null,wrapper);
        map.put("update",update); //返回1代表成功，0代表失败
        return map;
    }

    public Map<String, Object> staff_select(String company_name) {
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
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("company",company_name);
        List<Staff> staff = staffMapper.selectList(wrapper);
        System.out.println(staff);
        map.put("staff",staff);
        return map;
    }

    public Map<String, Object> staff_update(String id_number,String company, String departmentName, Date entryTime, int salary,
                                            int employ, String technique, String evaluation) {
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
        UpdateWrapper<Staff> wrapper = new UpdateWrapper<>();
        wrapper.set("company",company).set("department_name",departmentName).set("entry_time",entryTime)
                .set("salary",salary).set("employ",employ).set("technique",technique).set("evaluation",evaluation)
                .eq("id_number", id_number);
        int update = staffMapper.update(null, wrapper);
        map.put("update",update); //返回1代表成功，0代表失败
        return map;
    }

    public Map<String, Object> staff_insert(Staff staff) {
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
        int insert = staffMapper.insert(staff);
        map.put("insert",insert); //返回1代表成功，0代表失败
        return map;
    }

    public Map<String, Object> staff_delete(String id_number) {
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
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",id_number);
        int delete = staffMapper.delete(wrapper);
        map.put("delete",delete); //返回1代表成功，0代表失败
        return map;
    }

    public Map<String, Object> staff_select_employ(String company_name, int employ) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();

        wrapper.eq("company",company_name);
        wrapper.eq("employ",employ);

        List<Staff> staff = staffMapper.selectList(wrapper);
        map.put("staff",staff);
        return map;
    }


    public Map<String, Object> register_new(String idNumber, String password, String repassword) {
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

        String msg="";
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number",idNumber);
        Staff satff_one = staffMapper.selectOne(wrapper);//查询id
        Staff staff =null;
        if (!password.equals(repassword)){
            msg="密码不一致";
        }else if (StringUtils.isEmpty(satff_one)){ //公司不存在
            staff = new Staff(idNumber, password);
            staffMapper.insert(staff);
            msg="注册成功";

        }else {
            msg="注册失败,账户已经存在";
        }
        map.put("msg",msg);
        map.put("staff",staff);
        return map;
    }

    public Map<String, Object> update_infomation(Staff staff) {
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
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("id_number", staff.getIdNumber());
        Staff staff1 = staffMapper.selectOne(wrapper);
        staff.setPassword(staff1.getPassword());

        int update = staffMapper.update(staff, wrapper);
        map.put("update",update); //返回1代表成功，0代表失败
        return map;
    }

    public Map<String, Object> update_password(String id_number, String old_password, String new_password, String re_password) {
            InputStream is = null;
            try {
                is = Resources.getResourceAsStream("mybatis-config.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession(true);
            StaffMapper staffMapper = session.getMapper(StaffMapper.class);

            Map<String, Object> map = new HashMap<>();
            String msg="";
            QueryWrapper<Staff> wrapper = new QueryWrapper<>();
            wrapper.eq("id_number",id_number).eq("password",old_password);
            Staff staff = staffMapper.selectOne(wrapper);
            if (!new_password.equals(re_password))
                    msg="新密码两次输入不一致";
            else if (StringUtils.isEmpty(staff)){
                msg="用户不存在或原密码错误";
            }else {
                Staff staff1 = staff;
                staff1.setPassword(new_password);
                int update = staffMapper.update(staff1, wrapper);
                if (update==1){
                    msg="密码修改成功";
                }else {
                    msg="密码修改失败";
                }
            }
            map.put("update",msg);
            return map;
    }

    public Map<String, Object> staff_select_one(String company_name, String id_number) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();

        wrapper.eq("company",company_name);
        wrapper.eq("id_number",id_number);

        Staff staff = staffMapper.selectOne(wrapper);
        map.put("staff",staff);
        return map;
    }

    public Map<String, Object> select_id_name(String company_name) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        StaffMapper staffMapper = session.getMapper(StaffMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        List<LinkedHashMap> datas = staffMapper.select_id_name(company_name);
        map.put("staff", datas);
        return map;
    }
}
