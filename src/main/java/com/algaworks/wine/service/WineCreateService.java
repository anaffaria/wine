package com.algaworks.wine.service;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.repository.Wines;
import com.algaworks.wine.storage.PhotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WineCreateService {

    @Autowired
    private Wines wines;

    @Autowired
    private PhotoStorage photostorage;

    public void save(Wine wine) {
        this.wines.save(wine);
    }

    public String addPhoto(Long code, MultipartFile photo) {
        Wine wine = wines.findOne(code);
        String photoName = photostorage.save(photo);
        wine.setPhoto(photoName);
        wines.save(wine);

        return photostorage.getUrl(photoName);
    }
}
