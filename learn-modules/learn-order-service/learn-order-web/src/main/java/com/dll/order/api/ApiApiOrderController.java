package com.dll.order.api;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author dll
 * @date 2025-07-21 09:58
 */
@RestController
public class ApiApiOrderController implements ApiOrderService{
    @Override
    public String qryOrderDetails() {
        return "from learn-order-web feign  api";
    }
}
