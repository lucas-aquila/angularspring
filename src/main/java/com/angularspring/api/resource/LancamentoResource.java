package com.angularspring.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.repository.ILancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private ILancamentoRepository lancamentoRepository;
	
	@GetMapping
	public List<Lancamento> listLancamentos() {
		return lancamentoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> findById(@PathVariable Long codigo){
		Optional<Lancamento> lancamento = this.lancamentoRepository.findById(codigo);
		if(!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lancamento.get());
	}
	
}
