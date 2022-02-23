package br.com.estoque.domain.exception;

import org.springframework.web.bind.annotation.RequestParam;

public class ProdutoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(@RequestParam String nomeProduto){
	        super(String.format("Produto with name %s not found in the system.", nomeProduto));
	    }
	    public ProdutoNotFoundException(Long id){
	        super(String.format("Produto with id %d not found in the system.", id));
	    }
}
