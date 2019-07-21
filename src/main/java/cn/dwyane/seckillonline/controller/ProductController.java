package cn.dwyane.seckillonline.controller;

import cn.dwyane.seckillonline.model.entity.Product;
import cn.dwyane.seckillonline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public String listPage(Model model, HttpServletRequest request, HttpServletResponse response){
        List<Product> products = productService.getProductList();
        model.addAttribute("products",products);
        return "list";
    }

    @RequestMapping("detail/{productId}")
    public String detail(Model model,@PathVariable("productId") long id){
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        return "detail";
    }

}
