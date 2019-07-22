package cn.dwyane.seckillonline.rabbitMQ;

import cn.dwyane.seckillonline.service.OrderService;
import cn.dwyane.seckillonline.service.impl.OrderServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void receive(String jsonMessage){
        JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonMessage);
        long productId = jsonObject.getLongValue("productId");
        int quantity = jsonObject.getIntValue("quantity");
        String userId = jsonObject.getString("userId");
        orderService.createOrder(productId,quantity,userId);
    }

}
