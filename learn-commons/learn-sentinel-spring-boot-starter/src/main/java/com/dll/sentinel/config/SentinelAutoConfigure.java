package com.dll.sentinel.config;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dll.common.model.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sentinel配置类
 *
 */
public class SentinelAutoConfigure {
    public SentinelAutoConfigure() {
        WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    }

    /**
     * 限流、熔断统一处理类
     */
    public class CustomUrlBlockHandler implements UrlBlockHandler {
        @Override
        public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
            ResultData result = ResultData.failed("flow-limiting");
            httpServletResponse.getWriter().print(JSONUtil.toJsonStr(result));
        }
    }
}
