package com.angularspring.api.repository.lancamento;

import java.util.List;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.repository.filter.LancamentoFilter;

public interface ILancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
