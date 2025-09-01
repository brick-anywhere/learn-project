package com.dll.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dll.stock.dao.StockMapper;
import com.dll.stock.entity.Stock;
import com.dll.stock.service.StockService;
import org.springframework.stereotype.Service;

/**
 * @author dll
 * @date 2025-07-19 17:00
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
}
