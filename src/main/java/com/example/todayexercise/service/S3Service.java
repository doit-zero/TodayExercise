package com.example.todayexercise.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.todayexercise.exception.domain.CommonErrorCode;
import com.example.todayexercise.exception.domain.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${S3_BUCKET_NAME}")
    private String bucket;

    public String uploadImageFile(MultipartFile imageFile) {
        String fileName = createFileName(imageFile.getOriginalFilename());
        if(imageFile.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_FOUND_IMG_FILES);
        }
        return memoryUpload(imageFile,fileName);
    }

    public String deleteImageFile(String imageUrl) {
        String fileName = extractFileName(imageUrl);
        amazonS3Client.deleteObject(bucket,fileName);
        return "삭제 완료";
    }


    public String memoryUpload(MultipartFile uploadFile, String fileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getSize());
        objectMetadata.setContentType(uploadFile.getContentType());

        try(InputStream inputStream = uploadFile.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket,fileName,inputStream,objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            return amazonS3Client.getUrl(bucket,fileName).toString();
        } catch (IOException e) {
            throw new CommonException(CommonErrorCode.FAIL_TO_SAVE);
        }

    }

    private String extractFileName(String imageUrl) {
        try{
            URI uri = new URI(imageUrl);
            String path = uri.getPath();

            String fileName = path.substring(path.lastIndexOf('/') + 1);
            return fileName;
        } catch (URISyntaxException e) {
            log.error("파일 이름 추출 실패: {}",imageUrl);
            throw new CommonException(CommonErrorCode.FAIL_TO_SAVE);
        }
    }


    public String createFileName(String fileName) {
        return UUID.randomUUID().toString();
    }



}
