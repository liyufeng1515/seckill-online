package cn.dwyane.seckillonline.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RabbitMQSender {

//    @Autowired
//    AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * RabbitTemplate 消息回执方法.
     *     实现
     *     AsyncRabbitTemplate.RabbitFuture<?> future = (AsyncRabbitTemplate.RabbitFuture)this.pending.get(correlationId);
     *             if (future != null) {
     *                 future.setNackCause(cause);
     *                 ((SettableListenableFuture)future.getConfirm()).set(ack);
     *             } else if (this.logger.isDebugEnabled()) {
     *                 this.logger.debug("Confirm: " + correlationData + ", ack=" + ack + (cause == null ? "" : ", cause: " + cause) + " no pending future - either canceled or the reply is already received");
     *     }
     * rabbitTemplate 实现自  amqpTemplate
     */
    @PostConstruct
    public void setUp(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if(ack){
                    System.out.println("MQ收到消息.");
                }else{
                    System.out.println("重发消息,或者...");
                }

            }
        });
    }

    public void sendTopic(String jsonMessage){
        //CorrelationData 可以放你的消息标识,来区别消息.
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName,RabbitMQConfig.routeKey,jsonMessage,new CorrelationData(jsonMessage));
        //amqpTemplate.convertAndSend(RabbitMQConfig.exchangeName,RabbitMQConfig.routeKey,jsonMessage);
    }
}
