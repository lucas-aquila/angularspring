package com.angularspring.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.repository.filter.LancamentoFilter;

public interface ILancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
