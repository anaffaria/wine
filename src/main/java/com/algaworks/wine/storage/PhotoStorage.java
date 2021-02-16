package com.algaworks.wine.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {
    public String save(MultipartFile photo);

    public String getUrl(String photoName);
}
