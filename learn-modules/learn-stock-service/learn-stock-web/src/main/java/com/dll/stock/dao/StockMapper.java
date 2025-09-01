package com.dll.stock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dll.stock.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
