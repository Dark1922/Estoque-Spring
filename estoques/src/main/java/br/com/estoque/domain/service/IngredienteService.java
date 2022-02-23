package br.com.estoque.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.assembler.IngredienteMapper;
import br.com.estoque.domain.dto.IngredienteDTO;
import br.com.estoque.domain.dto.input.IngredienteInput;
import br.com.estoque.domain.exception.IngredienteNotFoundException;
import br.com.estoque.domain.exception.IngredienteStockExceededException;
import br.com.estoque.domain.exception.ProdutoNotFoundException;
import br.com.estoque.domain.model.Ingrediente;
import br.com.estoque.domain.model.Produto;
import br.com.estoque.domain.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private IngredienteMapper ingredienteMapper;

	@Autowired
	private ProdutoService produtoService;
	

	@Transactional
	public IngredienteDTO saveProductIngrediente(IngredienteInput ingredienteInput, Long produtoId)
			throws ProdutoNotFoundException {
		Produto produto = produtoService.verifyIfExistId(produtoId);
		Ingrediente ingredienteAtual = ingredienteMapper.toDomainObject(ingredienteInput);
		ingredienteAtual.setProduto(produto);

		return ingredienteMapper.toModel(ingredienteRepository.save(ingredienteAtual));
	}

	@Transactional
	public List<IngredienteDTO> listAll() {
		return ingredienteMapper.toCollectionModel(ingredienteRepository.findAll());
	}

	@Transactional
	public void deleteById(Long id) throws IngredienteNotFoundException {
		verifyIfExist(id);
		ingredienteRepository.deleteById(id);
	}

	@Transactional
	public Ingrediente verifyIfExist(Long id) throws IngredienteNotFoundException {
		return ingredienteRepository.findById(id).orElseThrow(() -> new IngredienteNotFoundException(id));
	}

	@Transactional
	public IngredienteDTO increment(Long id, int quantityToIncrement)
			throws IngredienteNotFoundException, IngredienteStockExceededException {

		Ingrediente ingredienteToIncementStock = verifyIfExist(id);

		int quantityAfterIncrement = quantityToIncrement + ingredienteToIncementStock.getQuantity();
		if (quantityAfterIncrement <= ingredienteToIncementStock.getMaxStock()) {
			ingredienteToIncementStock.setQuantity(ingredienteToIncementStock.getQuantity() + quantityToIncrement);
			Ingrediente incrementedProductStock = ingredienteRepository.save(ingredienteToIncementStock);
			return ingredienteMapper.toModel(incrementedProductStock);
		}
		throw new IngredienteStockExceededException(id, quantityToIncrement);
	}
	
	@Transactional
	public IngredienteDTO decrement(Long id, int quantityToDecrement)
			throws IngredienteNotFoundException, IngredienteStockExceededException {

		Ingrediente ingredienteToDecrementStock = verifyIfExist(id);

		int quantityAfterIncrement = quantityToDecrement + ingredienteToDecrementStock.getQuantity();
		
		if (quantityToDecrement <= ingredienteToDecrementStock.getQuantity()) {
			ingredienteToDecrementStock.setQuantity(ingredienteToDecrementStock.getQuantity() - quantityToDecrement);
			Ingrediente incrementedProductStock = ingredienteRepository.save(ingredienteToDecrementStock);
			return ingredienteMapper.toModel(incrementedProductStock);
		}
		throw new IngredienteStockExceededException(id, quantityToDecrement);
	}
	
}
