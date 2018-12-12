package com.angularspring.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularspring.api.domain.Categoria;
import com.angularspring.api.repository.ICategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listAll() {
		return this.categoriaRepository.findAll();
	}

}
