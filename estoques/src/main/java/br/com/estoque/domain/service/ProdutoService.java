package br.com.estoque.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.assembler.ProdutoMapper;
import br.com.estoque.domain.dto.ProdutoDTO;
import br.com.estoque.domain.dto.input.ProdutoInput;
import br.com.estoque.domain.exception.ProdutoAlreadyRegisteredException;
import br.com.estoque.domain.exception.ProdutoNotFoundException;
import br.com.estoque.domain.model.Produto;
import br.com.estoque.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoMapper produtoMapper;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public ProdutoDTO createProduto(ProdutoInput produtoInput) throws ProdutoAlreadyRegisteredException {
		verifyIfALreadyRegistered(produtoInput.getName());
		Produto produtoAtual = produtoMapper.toDomainObject(produtoInput);
		return produtoMapper.toModel(produtoRepository.save(produtoAtual));
	}

	@Transactional
	public List<ProdutoDTO> findAll() {
		return produtoMapper.toCollectionModel(produtoRepository.findAll());
	}

	private void verifyIfALreadyRegistered(String name) throws ProdutoAlreadyRegisteredException {
		Optional<Produto> optSavedBeer = produtoRepository.findByName(name);
		if (optSavedBeer.isPresent()) {
			throw new ProdutoAlreadyRegisteredException(name);
		}
	}

	public ProdutoDTO findById(Long id) throws ProdutoNotFoundException {
		return produtoMapper.toModel(verifyIfExistId(id));
	}

	public Produto verifyIfExistId(Long produtoId) throws ProdutoNotFoundException {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNotFoundException(produtoId));
	}

}
