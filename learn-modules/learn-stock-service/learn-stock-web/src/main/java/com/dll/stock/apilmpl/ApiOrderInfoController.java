package com.dll.stock.apilmpl;


import com.dll.log.monitor.LogUtil;
import com.dll.stock.api.ApiStockService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dll
 * @version 1.0.0
 * 类描述:
 */
@RestController
public class ApiOrderInfoController implements ApiStockService {

//    @Autowired
//    private KwExamPlanService planService;

    @Override
    public String qryPlanCode(Integer examPlanId) {
        try {
            return "【learn-stock-web】  feign 调用返回的数据";
        } catch (Exception e) {
            LogUtil.error("查询失败: {}", e);
            return null;
        }
    }
}
