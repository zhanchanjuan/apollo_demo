package com.honeycomb.apollodemo.java;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * java获取apollo配置
 *
 * @author Hawk
 * @date 2021/1/18
 */
@SpringBootApplication
public class JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    /**
     * DemoController
     */
    @RestController
    public class DemoController {

        @GetMapping(value = "/demo")
        public String getApollo() {
            // 获取默认配置application.properties
            Config defaultConfig = ConfigService.getAppConfig();
            // key
            String defaultConfigKey = "timeout";
            // 若取不到给默认值
            String defaultConfigDefaultValue = "-1";
            String value1 = defaultConfig.getProperty(defaultConfigKey, defaultConfigDefaultValue);

            // 获取java.xml配置
            ConfigFile configFile = ConfigService.getConfigFile("java", ConfigFileFormat.XML);
            String value2 = configFile.getContent();
            return "timeout:" + value1 + "\nxml:\n" + value2;
        }
    }

}
