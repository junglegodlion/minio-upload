package com.jarcheng.upload.controller;


import com.jarcheng.upload.entity.Result;
import com.jarcheng.upload.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "MinIO对象存储管理")
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    MinioService minioService;


    @ApiOperation("图片上传")
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioService.upload(file);
            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("url", url);
            return Result.SUCCESS(urlMap);
        } catch (Exception e) {
            return Result.FAIL("上传失败");
        }
    }

    @ApiOperation("头像上传")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioService.upload(file);
            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("url", url);
            return Result.SUCCESS(urlMap);
        } catch (Exception e) {
            return Result.FAIL("上传失败");
        }
    }

    @ApiOperation("背景上传")
    @PostMapping("/uploadBackground")
    public Result uploadBackground(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioService.upload(file);

            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("url", url);
            return Result.SUCCESS(urlMap);
        } catch (Exception e) {
            return Result.FAIL("上传失败");
        }
    }

    @ApiOperation("上传视频")
    @PostMapping("/uploadVideo")
    public Result uploadVideo(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String url = minioService.upload(file);

            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("url", url);
            return Result.SUCCESS(urlMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAIL("上传失败");
        }
    }

    @ApiOperation("上传文章")
    @PostMapping("/uploadArticle")
    public Result uploadAricle(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioService.upload(file);
            return Result.SUCCESS("上传成功");
        } catch (Exception e) {
            return Result.FAIL("上传失败");
        }
    }

    @ApiOperation("删除")
    @GetMapping("/deleteObject")
    public Result deleteObject(@RequestParam("url") String url) {
        minioService.delete(url);
        return Result.SUCCESS();
    }
}
