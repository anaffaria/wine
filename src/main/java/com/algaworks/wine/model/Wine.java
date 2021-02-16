package com.algaworks.wine.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "wine")
@Getter
@Setter
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco")
    private String name;

    @NotNull(message = "Tipo do vinho é obrigatório")
    @Enumerated(EnumType.STRING)
    private WineType type;

    @NotNull(message = "Safra é obrigatório")
    private Integer harvest;

    @NotNull(message = "Volume é obrigatório")
    private Integer content;

    @NotNull(message = "Preço é obrigatório")
    private BigDecimal price;

    private String photo;

    @Transient
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return code.equals(wine.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public boolean hasPhoto() {
        return !StringUtils.isEmpty(photo);
    }
}
