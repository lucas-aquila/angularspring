package com.angularspring.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angularspring.api.domain.Lancamento;
import com.angularspring.api.event.RecursoCriadoEvent;
import com.angularspring.api.exceptionhandler.AngularSpringExceptionHandler.Erro;
import com.angularspring.api.repository.ILancamentoRepository;
import com.angularspring.api.repository.filter.LancamentoFilter;
import com.angularspring.api.service.LancamentoService;
import com.angularspring.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ILancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	//Publicador de Eventos da aplicação
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Lancamento> listLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> findById(@PathVariable Long codigo){
		Optional<Lancamento> lancamento = this.lancamentoRepository.findById(codigo);
		if(!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lancamento.get());
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> insert(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.insert(lancamento);
		
		//Dispara o evento criado
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}
	
}
