package cn.dwyane.seckillonline.service.impl;

import cn.dwyane.seckillonline.model.dao.ProductMapper;
import cn.dwyane.seckillonline.model.entity.Product;
import cn.dwyane.seckillonline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList() {
        List<Product> products = productMapper.findAll();
        return products;
    }

    @Override
    public Product getProductById(long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }
}
