package br.com.estoque.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.domain.model.FotoProduto;
import br.com.estoque.domain.repository.ProdutoRepository;

@Service
public class CatalagoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		return produtoRepository.save(foto);
	}
}
