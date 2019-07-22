package cn.dwyane.seckillonline.controller;


import cn.dwyane.seckillonline.rabbitMQ.RabbitMQConfig;
import cn.dwyane.seckillonline.rabbitMQ.RabbitMQSender;
import cn.dwyane.seckillonline.service.OrderService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @RequestMapping("createOrder")
    public String createOrder(@RequestParam long productId,@RequestParam int quantity,@RequestParam String userId){
        //如果用MQ流量削峰,前端请求的消息通知可能就需要是异步方式的了
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("productId",productId);
//        jsonObject.put("quantity",quantity);
//        jsonObject.put("userId",userId);
//        //如果使用MQ ,需要异步通知用户
//        rabbitMQSender.sendTopic(jsonObject.toJSONString());
//        return "请注意查收手机短信,查看抢购结果.";
        boolean status = orderService.createOrder(productId,quantity,userId);
        if(status){
            return "创建订单成功";
        }
        return "创建订单失败";
    }
}
