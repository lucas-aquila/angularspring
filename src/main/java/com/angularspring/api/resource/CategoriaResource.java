package com.angularspring.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.angularspring.api.domain.Categoria;
import com.angularspring.api.repository.ICategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	/**
	 * @return
	 */
	@GetMapping
	public List<Categoria> listAll() {
		return this.categoriaRepository.findAll();
	}
	
	/**
	 * @param categoria
	 * @param response
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = this.categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	/**
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> findById(@PathVariable Long codigo) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
		if(!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(categoria.get());
	}

}
