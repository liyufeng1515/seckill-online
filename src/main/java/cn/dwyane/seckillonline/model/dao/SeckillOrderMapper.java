package cn.dwyane.seckillonline.model.dao;

import cn.dwyane.seckillonline.model.entity.SeckillOrder;

public interface SeckillOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    SeckillOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);
}