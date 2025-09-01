package com.dll.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dll.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
