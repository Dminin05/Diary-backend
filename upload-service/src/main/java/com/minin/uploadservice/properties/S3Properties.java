package com.minin.uploadservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.bucket")
@Data
public class S3Properties {
    private String name;
    private String accessKeyId;
    private String secretAccessKey;
}
