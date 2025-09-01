package com.dll.common.ribbon.config;

import com.dll.common.config.LearnEnvironment;
import com.dll.log.monitor.LogUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RestTemplate 配置
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "learn-project.rest-template")
@ToString
public class RestTemplateProperties {
    /**
     * 最大链接数
     */
    private int maxTotal = 200;
    /**
     * 同路由最大并发数
     */
    private int maxPerRoute = 50;
    /**
     * 读取超时时间 ms
     */
    private int readTimeout = 350000;
    /**
     * 链接超时时间 ms
     */
    private int connectTimeout = 100000;

    public RestTemplateProperties() {
        //if (当某一环境时使用特定的配置参数) {
        //	this.maxTotal = 500;
        //	this.maxPerRoute = 200;
        //	this.readTimeout = 2000;
        //	this.connectTimeout = 1000;
        //}
        LogUtil.info("检测到环境为:{}, 最终使用的配置参数:{}", LearnEnvironment.getEnvironment(), this.toString());
    }
}
