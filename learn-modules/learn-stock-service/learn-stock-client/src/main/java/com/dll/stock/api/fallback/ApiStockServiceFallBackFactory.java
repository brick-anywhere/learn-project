package com.dll.stock.api.fallback;

import com.dll.log.monitor.LogUtil;
import com.dll.stock.api.ApiStockService;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author dll
 * @version 1.0.0
 * @date 2020-03-30 14:49
 * 类描述:
 */
@Component
public class ApiStockServiceFallBackFactory implements FallbackFactory<ApiStockService> {

    @Override
    public ApiStockService create(Throwable cause) {
        return new ApiStockService() {
            @Override
            public String qryPlanCode(Integer examPlanId) {
                LogUtil.error("交易异常: 发生服务熔断.");
                return null;
            }

        };
    }
}
