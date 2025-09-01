package com.dll.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dll.order.dao.OrderMapper;
import com.dll.order.entity.Order;
import com.dll.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author dll
 * @date 2025-07-19 18:12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
