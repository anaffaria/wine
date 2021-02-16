package com.algaworks.wine.controller;

import com.algaworks.wine.dto.Photo;
import com.algaworks.wine.service.WineCreateService;
import com.algaworks.wine.storage.PhotoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
public class PhotosController {

    @Autowired
    private WineCreateService wineCreateService;

    @Autowired(required = false)
    private PhotoReader photoReader;

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public Photo upload(@PathVariable Long code, @RequestParam("files[]") MultipartFile[] files) {
        String url = wineCreateService.addPhoto(code, files[0]);

        return new Photo(url);
    }

    @RequestMapping("/{name:.*}")
    public byte[] retrieve(@PathVariable String name) {
        return photoReader.retireve(name);
    }
}
