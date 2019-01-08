package com.angularspring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.repository.lancamento.ILancamentoRepositoryQuery;

@Repository
public interface ILancamentoRepository extends JpaRepository<Lancamento, Long>, ILancamentoRepositoryQuery {

}
