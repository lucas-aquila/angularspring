package com.angularspring.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

}
