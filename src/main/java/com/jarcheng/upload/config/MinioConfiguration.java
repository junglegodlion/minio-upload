package com.jarcheng.upload.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Autowired
    Minio minio;

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {

        return new MinioClient(minio.getENDPOINT(), minio.getACCESS_KEY(), minio.getSECRET_KEY());
    }
}
