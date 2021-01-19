package com.honeycomb.apollodemo.spring.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.honeycomb.apollodemo.spring.bean.TestJavaConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认读取application.properties
 * 此处设置为读取application.properties与spring.yml两个文件配置
 *
 * @author Hawk
 * @date 2021/1/18
 */
@Configuration
@EnableApolloConfig(value = {"application", "spring.yml"})
public class AppConfig {

    @Bean
    public TestJavaConfigBean javaConfigBean() {
        return new TestJavaConfigBean();
    }

}
