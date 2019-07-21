package cn.dwyane.seckillonline.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "login";
    }
}
