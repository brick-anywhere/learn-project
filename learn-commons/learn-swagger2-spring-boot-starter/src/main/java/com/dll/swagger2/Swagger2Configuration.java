package com.dll.swagger2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 *  ConditionalOnProperty 控制某个configuration是否生效。
 *  matchIfMissing：  缺少该property时是否可以加载。
 *  name 数组，property完整名称或部分名称（可与prefix组合使用，组成完整的property名称），与value不可同时使用。 作用与value一样
 */
@Configuration
@ConditionalOnProperty(name = "dll.swagger.enabled", matchIfMissing = true)
@Import({
        Swagger2DocumentationConfiguration.class
})
public class Swagger2Configuration {
}
