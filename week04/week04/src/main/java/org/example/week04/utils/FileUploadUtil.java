package org.example.week04.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.example.week04.exception.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {
    // 用绝对路径，彻底解决目录创建失败问题
    private static final String UPLOAD_DIR = "D:/week04_upload/";

    static {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
            log.info("上传目录创建成功: {}", UPLOAD_DIR);
        }
    }

    public static String upload(MultipartFile file) throws IOException {
        // 1. 校验文件非空
        if (file.isEmpty()) {
            throw new BusinessException(400, "文件不能为空");
        }

        // 2. 校验文件名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BusinessException(400, "文件名不能为空");
        }

        // 3. 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        // 只允许图片，避免非法文件
        if (!".jpg".equals(suffix) && !".jpeg".equals(suffix) && !".png".equals(suffix)) {
            throw new BusinessException(400, "不支持的文件类型: " + suffix);
        }

        // 4. UUID重命名，解决重复
        String fileName = UUID.randomUUID() + suffix;
        File dest = new File(UPLOAD_DIR + fileName);

        // 5. 保存文件
        file.transferTo(dest);
        log.info("文件上传成功: {}", fileName);
        return fileName;
    }
}