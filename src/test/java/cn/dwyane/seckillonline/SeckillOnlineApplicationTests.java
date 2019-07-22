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
    private static final long productId = 100000;

    //模拟请求数量
    private static final int treadNum = 2000;

    private CountDownLatch countDownLatch = new CountDownLatch(treadNum);

    @Test
    public void contextLoads() throws InterruptedException {
        Thread[] threads = new Thread[treadNum];
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
                    orderService.createOrder(productId,1,userId);
                }
            });
            threads[i] = thread;
            thread.start();
            countDownLatch.countDown();
        }
        for(Thread thread:threads){
            thread.join();
        }
    }

}
