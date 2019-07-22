package cn.dwyane.seckillonline.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String queueName = "seckill.queue";
    public static final String exchangeName = "seckill.exchange";
    public static final String routeKey = "seckill.route";

    @Bean("message")
    public Queue queueMessage (){
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routeKey);
    }

}
