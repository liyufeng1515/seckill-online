package cn.dwyane.seckillonline.service;

import cn.dwyane.seckillonline.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();

    Product getProductById(long id);
}
