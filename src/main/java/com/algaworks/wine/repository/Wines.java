package com.algaworks.wine.repository;

import com.algaworks.wine.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Wines extends JpaRepository<Wine, Long> {

}
