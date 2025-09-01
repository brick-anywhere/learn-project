package com.dll.order.api;


import com.dll.common.constant.ServiceNameConstants;
import com.dll.order.api.fallback.OrderServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = ServiceNameConstants.ORDER_SERVICE_WEB, fallbackFactory = OrderServiceFallBackFactory.class, decode404 = true)
public interface ApiOrderService {

    @RequestMapping(path = "/qryOrderDetails",method = RequestMethod.POST)
    String qryOrderDetails();
}
