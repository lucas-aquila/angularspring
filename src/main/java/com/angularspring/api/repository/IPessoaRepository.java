package com.angularspring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angularspring.api.domain.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{

}
