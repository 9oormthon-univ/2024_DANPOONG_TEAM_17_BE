package com.munhwahansang.back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
public class ImageService {

    private final String bucketName = "munhwa-bucket";

    private final S3Client s3Client;

    public ImageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            // S3에 업로드 요청
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            // 업로드된 파일의 URL 반환
            return String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName, Region.AP_NORTHEAST_2.id(), fileName);

        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}
