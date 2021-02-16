package com.algaworks.wine.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.FileSystems.getDefault;

@Profile("storage-local")
@Component
public class PhotoStorageLocal implements PhotoStorage, PhotoReader {
    private Path local;

    PhotoStorageLocal() {
        this.local = getDefault().getPath(System.getenv("HOME"), ".winephotos");

        try {
            Files.createDirectories(this.local);
        } catch (IOException e){
            throw new RuntimeException("Erro criando pasta para salvar foto", e);
        }
    }

    @Override
    public String save(MultipartFile photo) {
        String photoName = photo.getOriginalFilename();

        try {
            photo.transferTo(new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + photoName));
        } catch (IOException e) {
            throw new RuntimeException("Erro salvando foto", e);
        }

        return photoName;
    }

    @Override
    public String getUrl(String photoName) {
        return "http://localhost:8080/photos/" + photoName;
    }

    @Override
    public byte[] retireve(String name) {
        try {
            return Files.readAllBytes(this.local.resolve(name));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao recuperar a foto", e);
        }
    }
}
