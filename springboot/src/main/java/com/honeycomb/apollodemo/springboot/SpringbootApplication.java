package com.honeycomb.apollodemo.springboot;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * springboot接入apollo配置中心
 *
 * @author Hawk
 * @date 2021/1/18
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    /**
     * DemoController
     */
    @RestController
    public class DemoController {

        @Value(value = "${system.timeout:0}")
        private int timeout;

        @Value(value = "${system.useCache:true}")
        private boolean useCache;

        @GetMapping(value = "/demo")
        public String test() {
            return "timeout:" + timeout + "\nuseCache:" + useCache;
        }
    }

    /**
     * 在服务启动后注册配置监听
     */
    @PostConstruct
    public void init() {
        // 指定监听springboot.yml命名空间配置
        Config config = ConfigService.getConfig("springboot.yml");
        config.addChangeListener(changeEvent -> {
            System.out.println("Changes for namespace " + changeEvent.getNamespace());
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
            }
        });
    }

}
