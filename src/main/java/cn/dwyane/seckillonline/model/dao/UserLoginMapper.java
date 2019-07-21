package cn.dwyane.seckillonline.model.dao;

import cn.dwyane.seckillonline.model.entity.UserLogin;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long userLoginId);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Long userLoginId);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}