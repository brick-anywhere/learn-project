package com.dll.stock.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dll.common.exception.BusinessException;
import com.dll.common.model.ResultData;
import com.dll.log.monitor.LogUtil;
import com.dll.stock.entity.Stock;
import com.dll.stock.service.StockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author dll
 * @date 2025-07-19 16:59
 */
@RestController
@Api(value = "库存相关Api信息", tags = "库存相关Api信息")
@RequestMapping(path = "/stock")
@RefreshScope
public class StockController {

    @Autowired
    private StockService stockService;
    @Value("${server.port}")
    private String serverPort;
    @Value("${version.info}")
    private String versionInfo;

    /**
     * 接口测试
     *
     * @param
     * @return
     */
    @PostMapping(value = "/pass")
    public ResultData<?> pass(@RequestBody Stock stock) {
        try {
            System.out.println("============【进入/stock/pass】========");
            Stock byId = stockService.getById(1);
            String str = "============【返回/stock/pass】========";
            return ResultData.ok(byId);
        } catch (BusinessException eB) {
            LogUtil.error("详情     业务异常:{}" + eB.getMessage());
            return ResultData.failed(eB.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(" 详情。{}", e.getMessage());
            return ResultData.failed(e.getMessage());
        }

    }

    @GetMapping(value = "/test")
    public String test() {
        try {
            System.out.println("============【进入/stock/test】========" + serverPort + versionInfo);
            String str = "============【返回/stock/test】========" + serverPort + versionInfo;
            return str;
        } catch (BusinessException eB) {
            LogUtil.error("详情     业务异常:{}" + eB.getMessage());
            return "失败";
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(" 详情。{}", e.getMessage());
            return "失败";
        }

    }
}
