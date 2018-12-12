package com.angularspring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularspring.api.domain.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria,Long> {

}
