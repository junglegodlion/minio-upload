package com.jarcheng.upload.service.impl;

import com.jarcheng.upload.config.Minio;
import com.jarcheng.upload.service.MinioService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    @Autowired
    Minio minio;
    @Autowired
    MinioClient minioClient;

    @Override
    public String upload(MultipartFile file) throws Exception {
        String BUCKET_NAME = minio.getBUCKET_NAME();
        boolean isExist = minioClient.bucketExists(BUCKET_NAME);
        if (isExist) {
            log.info("桶已存在");
        } else {
            minioClient.makeBucket(BUCKET_NAME);
            minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
        }
        String filename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String objectName = sdf.format(new Date()) + filename;
        minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
        log.info("文件上传成功");
        return "/resource" + "/" + BUCKET_NAME + "/" + objectName;
    }

    @Override
    public int delete(String url) {
        try {
            String[] splits = url.split("/");
            String bucket = splits[2];
            String fileName = splits[3];
            log.info(bucket + "   " + fileName);
            minioClient.removeObject(bucket, fileName);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
