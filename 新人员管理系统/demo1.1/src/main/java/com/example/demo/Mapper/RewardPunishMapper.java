package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.RewardPunish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface RewardPunishMapper extends BaseMapper<RewardPunish> {
    @Insert("insert into reward_punish(id_number,sign,time,reason) values(#{idNumber},#{sign},#{time},#{reason})")
    void insert_into(RewardPunish rewardPunish);

    @Select("SELECT r.id_number,r.id,s.department_name AS department,s.name,r.sign,r.time,r.reason " +
            "FROM `reward_punish`  AS r LEFT JOIN staff AS s USING(id_number) WHERE s.company=#{company_name} AND r.sign=#{sign};")
    List<LinkedHashMap> company_name_sign(String company_name, String sign);

    @Select("SELECT r.id_number,r.id,s.department_name AS department,s.name,r.sign,r.time,r.reason " +
            "FROM `reward_punish`  AS r LEFT JOIN staff AS s USING(id_number) WHERE s.company=#{company_name} ;")
    List<LinkedHashMap> company_name_all(String company_name);
}
