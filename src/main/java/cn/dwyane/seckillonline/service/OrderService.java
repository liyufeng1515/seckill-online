package cn.dwyane.seckillonline.service;


public interface OrderService {

    boolean createOrder(long productId, int quantity,String userId);
}
