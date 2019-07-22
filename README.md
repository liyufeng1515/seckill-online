seckill-online

这个应用主要是对秒杀场景策略做了简单实现.

以及一些个人spring项目练习相关内容
mybatis 练习
jwt-token 登录验证
订单号生成策略 twitter 雪花算法
等等.

主要用到相关:
    springboot , mybatis , thymeleaf , redis , rabbitmq 等

    
针对秒杀场景,大致实现的策略:
    1.限制用户前端重复提交
    2.redis setnx 限制用户短时间频繁操作,多次请求问题.
    3.令牌机制 限流,拿到令牌的才可以继续抢购.
    4.rabbitmq 流量削峰
    5.数据库乐观锁,防止超卖
    
    


