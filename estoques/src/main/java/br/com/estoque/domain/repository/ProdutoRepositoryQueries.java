package br.com.estoque.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.estoque.domain.model.FotoProduto;

@Repository
public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);

	void delete(FotoProduto foto);

}
