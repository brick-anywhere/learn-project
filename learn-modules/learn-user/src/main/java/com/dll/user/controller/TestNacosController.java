package com.dll.user.controller;

import com.dll.common.model.ResultData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dll
 * @date 2025-07-13 20:27
 */
@Api(value = "测试Nacos模块", tags = "测试Nacos模块")
@RestController
@RequestMapping(path = "/nacos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class TestNacosController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public ResultData getConfigInfo( ){
        System.out.println("######################### = " + configInfo);
        System.out.println(configInfo);
        return ResultData.ok(configInfo);
    }
}
