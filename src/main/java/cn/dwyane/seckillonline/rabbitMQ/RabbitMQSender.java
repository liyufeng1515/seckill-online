package cn.dwyane.seckillonline.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendTopic(String jsonMessage){
        amqpTemplate.convertAndSend(RabbitMQConfig.exchangeName,RabbitMQConfig.routeKey,jsonMessage);
    }
}
