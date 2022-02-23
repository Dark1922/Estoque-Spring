package br.com.estoque.domain.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.domain.model.FotoProduto;
import br.com.estoque.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public FotoProduto save(FotoProduto foto) {
		return entityManager.merge(foto);
	}

	@Override
	@Transactional
	public void delete(FotoProduto foto) {
		entityManager.remove(foto);

	}

}
