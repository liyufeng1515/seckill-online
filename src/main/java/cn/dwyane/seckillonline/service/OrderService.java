package cn.dwyane.seckillonline.service;


public interface OrderService {

    boolean createOrder(long productId, Integer quantity,String userId);
}
