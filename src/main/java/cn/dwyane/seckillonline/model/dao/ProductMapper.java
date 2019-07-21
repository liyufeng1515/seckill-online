package cn.dwyane.seckillonline.model.dao;

import cn.dwyane.seckillonline.model.entity.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findAll();
}