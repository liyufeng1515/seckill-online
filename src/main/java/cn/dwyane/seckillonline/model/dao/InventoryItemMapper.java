package cn.dwyane.seckillonline.model.dao;

import cn.dwyane.seckillonline.model.entity.InventoryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryItemMapper {
    int deleteByPrimaryKey(Long inventoryItemId);

    int insert(InventoryItem record);

    int insertSelective(InventoryItem record);

    InventoryItem selectByPrimaryKey(Long inventoryItemId);

    int updateByPrimaryKeySelective(InventoryItem record);

    int updateByPrimaryKey(InventoryItem record);

    List<InventoryItem> selectByProductId(long productId);

    int deductInventoryItem(@Param("productId") long productId,@Param("quantity") Integer quantity);
}