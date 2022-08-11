package com.example.demo.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.CompanyMapper;
import com.example.demo.bean.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class CompanyService {
//    private CompanyMapper employerMapper=null;
//    public CompanyService(){
//        InputStream is = null;
//        try {
//            is = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
//        SqlSession session = sessionFactory.openSession(true);
//        this.employerMapper = session.getMapper(CompanyMapper.class);
//    }

    /**
     * 登录
     * @param company_name
     * @param password
     * @return
     */
    public Map<String, Object> login(String company_name,String password){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        CompanyMapper employerMapper = session.getMapper(CompanyMapper.class);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company_name).eq("password",password);
        Company company_one = employerMapper.selectOne(wrapper);
        String msg="";
        if (StringUtils.isEmpty(company_one)|| !company_one.getPassword().equals(password)){
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
     * @param company
     * @return
     */
    public Map<String , Object> register(Company company){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        CompanyMapper employerMapper = session.getMapper(CompanyMapper.class);


        String msg="";
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company.getCompanyName());
        Company company_one = employerMapper.selectOne(wrapper);//查询id
        if (StringUtils.isEmpty(company_one)){ //公司不存在
            employerMapper.insert(company);
            msg="注册成功";
        }else {
            msg="注册失败";
        }
        map.put("msg",msg);
        map.put("staff",company);
        return map;
    }

    public Map<String, Object> select_company() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        CompanyMapper company = session.getMapper(CompanyMapper.class);

        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        List<Company> datas = company.selectList(wrapper);

        map.put("datas",datas);
        return map;
    }

    public Map<String, Object> select_company_name_and_id() {

        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        CompanyMapper company = session.getMapper(CompanyMapper.class);

        Map<String, Object> map = new HashMap<>();
        List<LinkedHashMap> datas = company.select_company_name_and_id();

        map.put("datas",datas);
        return map;
    }

    public Map<String, Object> update_password(String company_name, String old_password, String new_password, String re_password) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);
        CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);

        Map<String, Object> map = new HashMap<>();
        String msg="";
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_name",company_name).eq("password",old_password);
        Company company = companyMapper.selectOne(wrapper);
        if (!new_password.equals(re_password))
            msg="新密码两次输入不一致";
        else if (StringUtils.isEmpty(company)){
            msg="用户不存在或原密码错误";
        }else {
            Company company1 = company;
            company1.setPassword(new_password);
            int update = companyMapper.update(company1, wrapper);
            if (update==1){
                msg="密码修改成功";
            }else {
                msg="密码修改失败";
            }
        }
        map.put("update",msg);
        return map;
    }
}
