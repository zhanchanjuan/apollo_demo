package com.honeycomb.apollodemo.spring;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.honeycomb.apollodemo.spring.bean.TestJavaConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * spring使用java注解获取配置
 *
 * @author Hawk
 * @date 2021/1/18
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * DemoController
     */
    @RestController
    public class DemoController {

        @Autowired
        TestJavaConfigBean testJavaConfigBean;

        @GetMapping(value = "/demo")
        public TestJavaConfigBean test() {
            return testJavaConfigBean;
        }
    }

    @PostConstruct
    public void init() {
        // 监听默认命名空间配置application.properties,有变化时将触发回调
        Config config = ConfigService.getAppConfig();
        config.addChangeListener(changeEvent -> {
            System.out.println("Changes for namespace " + changeEvent.getNamespace());
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
            }
        });
    }

}
