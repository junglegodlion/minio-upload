package com.jarcheng.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "minio")
@Component
public class Minio {
    private String ENDPOINT;
    private String BUCKET_NAME;
    private String ACCESS_KEY;
    private String SECRET_KEY;
}
