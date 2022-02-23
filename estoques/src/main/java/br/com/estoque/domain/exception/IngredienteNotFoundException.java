package br.com.estoque.domain.exception;

public class IngredienteNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public IngredienteNotFoundException(String ingrediente){
	        super(String.format("Ingrediente with name %s not found in the system.", ingrediente));
	    }
	    public IngredienteNotFoundException(Long id){
	        super(String.format("Ingrediente with id %d not found in the system.", id));
	    }
}
