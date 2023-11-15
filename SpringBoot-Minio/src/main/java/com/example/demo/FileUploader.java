package com.example.demo;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class FileUploader {

    public static void makeSureBucketExists(MinioClient minioClient, String bucketName) throws Exception {
        // 确保 bucketName 存在，否则会报错
        boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build());
        if (!found) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        } else {
            System.out.println("Bucket already exists.");
        }
    }

    public static void main(String[] args)
            throws Exception {

        // 客户端
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("ROOTUSER", "CHANGEME123")
                .build();

        // bucket
        String bucketName = "bucket";

        // 确保bucket存在
        makeSureBucketExists(minioClient, bucketName);

        // 参数
        UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                .bucket(bucketName)
                .object("demo.txt")
                .filename("demo.txt")
                .build();

        // 上传文件
        minioClient.uploadObject(uploadObjectArgs);

        System.out.println("upload success");
    }
}