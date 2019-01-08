package com.angularspring.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.domain.Pessoa;
import com.angularspring.api.repository.ILancamentoRepository;
import com.angularspring.api.repository.IPessoaRepository;
import com.angularspring.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@Autowired
	private ILancamentoRepository lancamentoRepository;

	public Lancamento insert(@Valid Lancamento lancamento) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if(!pessoa.isPresent() || !pessoa.get().getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}

	
	
}
