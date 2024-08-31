package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * @param user
     */
    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long id);

    Integer countByMap(Map<String, Object> map);
}