package cn.dwyane.seckillonline;

import cn.dwyane.seckillonline.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillOnlineApplicationTests {

    @Autowired
    OrderService orderService;

    //商品编码
    private static final long product_id = 100000;

    //模拟请求数量
    private static final int treadNum = 200;

    private CountDownLatch countDownLatch = new CountDownLatch(treadNum);

    @Autowired

    @Test
    public void contextLoads() throws InterruptedException {

        for(int i=0;i < treadNum;i++){
            String userId = "testUser"+i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    orderService.createOrder(product_id,1,userId);
                }
            });
            thread.start();
            countDownLatch.countDown();
        }
        Thread.currentThread().join();

    }

}
