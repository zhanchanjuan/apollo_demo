package com.honeycomb.apollodemo.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author Hawk
 * @date 2021/1/18
 */
@Data
public class TestJavaConfigBean {

    @Value("${system.timeout:200}")
    private int timeout;

    @Value("${system.useCache:true}")
    private boolean useCache;

    @Value("${system.loginUrl:'http://www.baidu.com'}")
    private String loginUrl;

}
