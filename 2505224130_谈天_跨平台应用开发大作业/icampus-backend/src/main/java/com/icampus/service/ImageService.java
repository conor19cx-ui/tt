package com.icampus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icampus.mapper.ImageMapper;
import com.icampus.model.Image;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImageService extends ServiceImpl<ImageMapper, Image> {

    public boolean saveImage(String url, String type, Long relatedId, Long userId) {
        Image image = new Image();
        image.setUrl(url);
        image.setType(type);
        image.setRelatedId(relatedId);
        image.setUserId(userId);
        return this.save(image);
    }

    public List<Image> getImages(String type, Long relatedId) {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("related_id", relatedId);
        wrapper.orderByAsc("create_time");
        return this.list(wrapper);
    }

    public Map<Long, String> getFirstImages(List<Long> relatedIds, String type) {
        Map<Long, String> map = new HashMap<>();
        if (relatedIds == null || relatedIds.isEmpty()) {
            return map;
        }
        for (Long relatedId : relatedIds) {
            QueryWrapper<Image> wrapper = new QueryWrapper<>();
            wrapper.eq("type", type);
            wrapper.eq("related_id", relatedId);
            wrapper.orderByAsc("create_time");
            wrapper.last("limit 1");
            Image image = this.getOne(wrapper);
            if (image != null) {
                map.put(relatedId, image.getUrl());
            }
        }
        return map;
    }
}
