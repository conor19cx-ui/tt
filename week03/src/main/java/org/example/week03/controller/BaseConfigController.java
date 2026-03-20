package org.example.week03.controller;

import org.example.week03.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class BaseConfigController {

    @Value("${server.port}")
    private Integer port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${app.app-name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    @Value("${app.description}")
    private String description;

    @Value("${app.published}")
    private Boolean published;

    // 我在这里加了 UTF-8 编码，解决中文乱码！
    @GetMapping(value = "/base", produces = "application/json;charset=UTF-8")
    public Result<Map<String, Object>> getBaseConfigInfo() {
        Map<String, Object> map = Map.of(
                "port", port,
                "contextPath", contextPath,
                "applicationName", applicationName,
                "appName", appName,
                "version", version,
                "description", description,
                "published", published
        );
        return Result.success(map);
    }
}