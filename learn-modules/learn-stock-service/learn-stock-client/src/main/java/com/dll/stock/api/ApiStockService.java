package com.dll.stock.api;

import com.dll.common.constant.ServiceNameConstants;
import com.dll.stock.api.fallback.ApiStockServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author  dll
 * @version 1.0.0
 * @date 2020-03-30 14:48
 */
@FeignClient(name = ServiceNameConstants.STOCK_SERVICE_WEB, fallbackFactory = ApiStockServiceFallBackFactory.class, decode404 = true)
public interface ApiStockService {

    @RequestMapping(path = "/sub/stock",method = RequestMethod.POST)
    String qryPlanCode(@RequestParam(value = "examPlanId") Integer examPlanId);
}

