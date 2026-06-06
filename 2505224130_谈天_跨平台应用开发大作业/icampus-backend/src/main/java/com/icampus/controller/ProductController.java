package com.icampus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icampus.model.Product;
import com.icampus.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public Map<String, Object> getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Page<Product> pageResult = productService.getProducts(page, size, category, keyword, status);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/my")
    public Map<String, Object> getMyProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId,
            @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Page<Product> pageResult = productService.getMyProducts(page, size, userId, status);
        result.put("code", 200);
        result.put("data", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        result.put("pages", pageResult.getPages());
        return result;
    }

    @GetMapping("/detail")
    public Map<String, Object> getProductDetail(@RequestParam Long id) {
        Map<String, Object> result = new HashMap<>();
        Product product = productService.getProductById(id);
        if (product != null) {
            result.put("code", 200);
            result.put("data", product);
        } else {
            result.put("code", 404);
            result.put("message", "商品不存在");
        }
        return result;
    }

    @PostMapping("/publish")
    public Map<String, Object> publishProduct(@RequestBody Product product) {
        Map<String, Object> result = new HashMap<>();
        boolean success = productService.publish(product);
        if (success) {
            result.put("code", 200);
            result.put("message", "发布成功");
            result.put("data", product.getId());
        } else {
            result.put("code", 500);
            result.put("message", "发布失败");
        }
        return result;
    }

    @PostMapping("/updateStatus")
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        Long id = Long.parseLong(params.get("id").toString());
        String status = params.get("status").toString();
        boolean success = productService.updateStatus(id, status);
        if (success) {
            result.put("code", 200);
            result.put("message", "操作成功");
        } else {
            result.put("code", 500);
            result.put("message", "操作失败");
        }
        return result;
    }
}
