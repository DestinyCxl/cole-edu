package com.cole.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Cxl
 * @since: 2020-09-09
 **/
@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
//注意prefix要写到最后一个 "." 符号之前
public class OssProperties {

        private String endpoint;
        private String keyid;
        private String keysecret;
        private String bucketname;
}
