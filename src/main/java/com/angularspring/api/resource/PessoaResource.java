package com.angularspring.api.resource;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.angularspring.api.domain.Pessoa;
import com.angularspring.api.repository.IPessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	
	/**
	 * @param categoria
	 * @param response
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(pessoaSalva);
	}
	
	
	@RequestMapping("/{codigo}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long codigo){
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
		if(!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(pessoa.get());
	}

}
