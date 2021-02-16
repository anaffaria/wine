package com.algaworks.wine.storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Profile("storage-s3")
@Component
public class PhotoStorageS3 implements  PhotoStorage{

    @Autowired
    private AmazonS3 s3Client;

    public String save(MultipartFile photo) {
        String photoName = photo.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(photo.getContentType());
            metadata.setContentLength(photo.getSize());
            s3Client.putObject("wine", photoName, photo.getInputStream(), metadata);

        } catch (AmazonClientException | IOException e) {
            throw new RuntimeException("Falha ao salvar arquivo no S3");
        }

        return photoName;
    }

    public String getUrl(String photoName) {
        return "http://localhost:9444/s3/wine/" + photoName + "?noAuth=true";
    }
}
