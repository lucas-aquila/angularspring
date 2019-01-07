package com.angularspring.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angularspring.api.domain.Pessoa;
import com.angularspring.api.event.RecursoCriadoEvent;
import com.angularspring.api.repository.IPessoaRepository;
import com.angularspring.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	//Publicador de Eventos da aplicação
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * @param categoria
	 * @param response
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);
		
		//Dispara o evento criado
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	@RequestMapping("/{codigo}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long codigo){
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
		if(!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(pessoa.get());
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> update(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.update(codigo, pessoa);

		return ResponseEntity.ok(pessoaSalva);
	}
	
	//Atualização Parcial
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.updateAtivo(codigo, ativo);
	}
	

}
