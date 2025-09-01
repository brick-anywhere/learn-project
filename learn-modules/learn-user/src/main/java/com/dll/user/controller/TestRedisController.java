package com.dll.user.controller;

import com.dll.common.model.ResultData;
import com.dll.common.redis.template.StringRedisUtil;
import com.dll.user.dto.LearnDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dll
 * @date 2025-07-13 21:50
 */
@Api(value = "测试redis模块", tags = "测试redis模块")
@RestController
@RequestMapping(path = "/redis", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestRedisController {
    @Autowired
    StringRedisUtil stringRedisUtil;

    @GetMapping("/putLearnUserInfo")
    public ResultData putLearnUserInfo( ){
        LearnDto learnDto =  new LearnDto();
        learnDto.setUserName("tutu");
        learnDto.setUserInfo("redis中的数据");
        System.out.println("###########【放入】############# = " + learnDto);
        stringRedisUtil.set("learn",learnDto);
        LearnDto userLearn = (LearnDto) stringRedisUtil.get("learn");

        System.out.println("############【取出】############# = " + userLearn);

        return ResultData.ok("");
    }

}
