package com.dll.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dll
 * @date 2025-07-19 18:11
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
////    private

    @GetMapping("/order/test")
//    @SentinelResource("aaa")
    public String test() {
        //直接访问stock服务接口 获取数据
        String result = restTemplate.getForObject("http://learn-stock-web/stock/test", String.class);
        //响应数据
        return "Order Test Get " + result;
    };


    @GetMapping("/order/seataTest")
    public String seataTest() {
        //直接访问stock服务接口 获取数据
        String result = restTemplate.getForObject("http://learn-stock-web/stock/test", String.class);
        //响应数据
        return "Order Test Get " + result;
    };
}
