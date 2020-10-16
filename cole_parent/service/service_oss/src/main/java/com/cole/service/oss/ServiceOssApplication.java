package com.cole.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Cxl
 * @since: 2020-09-09
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan({"com.cole"})
@EnableDiscoveryClient
public class ServiceOssApplication {

        public static void main(String[] args) {
            SpringApplication.run(ServiceOssApplication.class, args);

    }
}
