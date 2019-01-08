package com.angularspring.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.angularspring.api.domain.Pessoa;
import com.angularspring.api.repository.IPessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	IPessoaRepository pessoaRepository;
	
	public Pessoa update(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).get();
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}

	public void updateAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).get();
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa findById(Long codigo) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
		if(!pessoa.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoa.get();
	}

}
