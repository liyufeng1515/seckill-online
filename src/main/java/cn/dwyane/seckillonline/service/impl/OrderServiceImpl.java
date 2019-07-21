package cn.dwyane.seckillonline.service.impl;

import cn.dwyane.seckillonline.model.dao.InventoryItemMapper;
import cn.dwyane.seckillonline.model.dao.ProductMapper;
import cn.dwyane.seckillonline.model.dao.SeckillOrderMapper;
import cn.dwyane.seckillonline.model.entity.InventoryItem;
import cn.dwyane.seckillonline.model.entity.Product;
import cn.dwyane.seckillonline.model.entity.SeckillOrder;
import cn.dwyane.seckillonline.service.OrderService;
import cn.dwyane.seckillonline.util.snowflake.SnowFlakeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
    //令牌机制
    private static ConcurrentLinkedQueue concurrentLinkedQueue = null;
    static {
        concurrentLinkedQueue = new ConcurrentLinkedQueue();
        for(int i=0;i<100;i++){
            concurrentLinkedQueue.offer(i);
        }
    }

    @Autowired
    InventoryItemMapper inventoryItemMapper;

    @Autowired
    SeckillOrderMapper seckillOrderMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public boolean createOrder(long productId, Integer quantity,String userId) {
        //令牌机制 拿到停牌的去参与秒杀
        Object tonken = concurrentLinkedQueue.poll();
        if(tonken == null){
            System.out.println("用户("+userId+")没有抢到令牌,无法购买.");
            return false;
        }
        //redis限制用户操作频率
        boolean setnx = stringRedisTemplate.opsForValue().setIfAbsent(userId,"用户ID",10000L,TimeUnit.MILLISECONDS);
        if(!setnx){
            System.out.println("10秒内用户("+userId+")操作频繁,被限制.");
            return false;
        }

        Product product = productMapper.selectByPrimaryKey(productId);
        if(null == product){
            System.out.println("产品不存在");
            return false;
        }
        //扣除库存
        int count = inventoryItemMapper.deductInventoryItem(productId,quantity);
        if(count !=1){
            System.out.println("创建订单失败");
            return false;
        }
        SeckillOrder seckillOrder = new SeckillOrder();
        //用雪花算法生成订单Id 并创建订单数据
        SnowFlakeWorker snowFlakeWorker = SnowFlakeWorker.getInstance();
        seckillOrder.setOrderId(snowFlakeWorker.nextId());
        //TODO 换成登录者ID
        seckillOrder.setCreatedBy(userId);
        seckillOrder.setCreatedStamp(new Date());
        seckillOrder.setProductId(productId);
        seckillOrder.setQuantity(quantity);
        seckillOrder.setGrandTotal(product.getUnitPrice()*quantity);
        seckillOrderMapper.insert(seckillOrder);
        System.out.println("创建订单成功"+seckillOrder.getOrderId());
        return true;
    }
}
