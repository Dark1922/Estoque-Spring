package br.com.estoque.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.domain.dto.ProdutoDTO;
import br.com.estoque.domain.dto.input.ProdutoInput;
import br.com.estoque.domain.exception.ProdutoAlreadyRegisteredException;
import br.com.estoque.domain.exception.ProdutoNotFoundException;
import br.com.estoque.domain.service.ProdutoService;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<ProdutoDTO> adicionar(@RequestBody @Valid ProdutoInput produtoInput)
			throws ProdutoAlreadyRegisteredException {

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.createProduto(produtoInput));

	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		return ResponseEntity.ok(produtoService.findAll());
	}

	@GetMapping(value = "/{id}")
	public ProdutoDTO findById(@PathVariable Long id) throws ProdutoNotFoundException {
		return produtoService.findById(id);
	}

}
