package br.com.estoque.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoAlreadyRegisteredException extends Exception{

	private static final long serialVersionUID = 1L;

	    public ProdutoAlreadyRegisteredException(String Produto){
	        super(String.format("Produto with name %s already registered in the system.", Produto));
	    }
}
