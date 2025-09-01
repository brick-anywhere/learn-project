package com.dll.order.api.fallback;

import com.dll.log.monitor.LogUtil;
import com.dll.order.api.ApiOrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author dll
 * @date 2025-07-19 18:06
 */
@Component
public class OrderServiceFallBackFactory implements FallbackFactory<ApiOrderService> {
    @Override
    public ApiOrderService create(Throwable cause) {
        return new ApiOrderService() {
            @Override
            public String qryOrderDetails() {
                LogUtil.error("交易异常: 发生服务熔断.");
                return null;
            }

        };
    }

}
