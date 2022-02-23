package br.com.estoque.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.assembler.IngredienteMapper;
import br.com.estoque.domain.dto.IngredienteDTO;
import br.com.estoque.domain.dto.QuantityDTO;
import br.com.estoque.domain.dto.input.IngredienteInput;
import br.com.estoque.domain.exception.IngredienteNotFoundException;
import br.com.estoque.domain.exception.IngredienteStockExceededException;
import br.com.estoque.domain.exception.ProdutoNotFoundException;
import br.com.estoque.domain.repository.IngredienteRepository;
import br.com.estoque.domain.service.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteMapper ingredienteMapper;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@PostMapping("/produto/{produtoId}")
	@ResponseStatus(HttpStatus.CREATED)
	public IngredienteDTO createIngrediente(@RequestBody @Valid IngredienteInput estoqueInput,
			@PathVariable Long produtoId) throws ProdutoNotFoundException {
		return ingredienteService.saveProductIngrediente(estoqueInput, produtoId);
	}

	@GetMapping
	public List<IngredienteDTO> listEstoques() {
		return ingredienteMapper.toCollectionModel(ingredienteRepository.findAll());
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws IngredienteNotFoundException {
		ingredienteService.deleteById(id);
	}

	@PatchMapping("/{id}/increment")
	public IngredienteDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO)
			throws IngredienteNotFoundException, IngredienteStockExceededException {
		return ingredienteService.increment(id, quantityDTO.getQuantity());
	}

	@PatchMapping("/{id}/decrement")
	public IngredienteDTO decrement(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO)
			throws IngredienteNotFoundException, IngredienteStockExceededException {
		return ingredienteService.decrement(id, quantityDTO.getQuantity());
	}

}
