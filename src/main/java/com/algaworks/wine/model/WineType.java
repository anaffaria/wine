package com.algaworks.wine.model;

import lombok.Getter;

@Getter
public enum WineType {
    TINTO("Tinto"),
    BRANCO("Branco"),
    ROSE("Rosé");

    private String description;

    WineType(String description){
        this.description = description;
    }


}
