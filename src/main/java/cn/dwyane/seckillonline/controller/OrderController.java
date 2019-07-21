package cn.dwyane.seckillonline.controller;


import cn.dwyane.seckillonline.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("createOrder")
    public String createOrder(@RequestParam long productId,@RequestParam int quantity,@RequestParam String userId){
        boolean status = orderService.createOrder(productId,quantity,userId);
        if(status){
            return "创建订单成功";
        }
        return "创建订单失败";
    }
}
