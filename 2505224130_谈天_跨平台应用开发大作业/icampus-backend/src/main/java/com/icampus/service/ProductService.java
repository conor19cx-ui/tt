package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.ProductMapper;
import com.icampus.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    public Page<Product> getProducts(Integer page, Integer size, String category, String keyword, String status) {
        Page<Product> pageParam = new Page<>(page, size);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (category != null && !category.equals("") && !category.equals("全部")) {
            wrapper.eq("category", category);
        }
        if (keyword != null && !keyword.equals("")) {
            wrapper.like("title", keyword);
        }
        if (status != null && !status.equals("")) {
            wrapper.eq("status", status);
        } else {
            wrapper.eq("status", "在售");
        }
        wrapper.orderByDesc("create_time");
        return this.page(pageParam, wrapper);
    }

    public Page<Product> getMyProducts(Integer page, Integer size, Long userId, String status) {
        Page<Product> pageParam = new Page<>(page, size);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (status != null && !status.equals("") && !status.equals("全部")) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("create_time");
        return this.page(pageParam, wrapper);
    }

    public Product getProductById(Long id) {
        return this.getById(id);
    }

    public boolean publish(Product product) {
        product.setStatus("在售");
        return this.save(product);
    }

    public boolean updateStatus(Long id, String status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        return this.updateById(product);
    }
}
