package com.icampus.controller;

import com.icampus.model.Image;
import com.icampus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long relatedId,
            @RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();

        if (file.isEmpty()) {
            result.put("code", 400);
            result.put("message", "文件为空");
            return result;
        }

        // 获取文件扩展名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成新文件名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;

        // 保存到本地目录
        String uploadDir = "c:/Users/35619/Desktop/icampus/static/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File destFile = new File(uploadDir + newFileName);
            file.transferTo(destFile);

            // 保存图片信息到数据库
            String url = "/static/uploads/" + newFileName;
            imageService.saveImage(url, type, relatedId, userId);

            result.put("code", 200);
            result.put("message", "上传成功");
            result.put("data", url);
        } catch (IOException e) {
            result.put("code", 500);
            result.put("message", "文件保存失败: " + e.getMessage());
        }

        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getImages(@RequestParam String type, @RequestParam Long relatedId) {
        Map<String, Object> result = new HashMap<>();
        List<Image> images = imageService.getImages(type, relatedId);
        result.put("code", 200);
        result.put("data", images);
        return result;
    }

    @GetMapping("/firstByProducts")
    public Map<String, Object> getFirstByProducts(@RequestParam String ids) {
        Map<String, Object> result = new HashMap<>();
        List<Long> idList = new ArrayList<>();
        for (String s : ids.split(",")) {
            if (!s.trim().isEmpty()) {
                idList.add(Long.parseLong(s.trim()));
            }
        }
        Map<Long, String> images = imageService.getFirstImages(idList, "product");
        result.put("code", 200);
        result.put("data", images);
        return result;
    }
}
