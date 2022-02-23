package br.com.estoque.domain.exception;

public class IngredienteStockExceededException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IngredienteStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Ingrediente with %d ID to increment informed exceeds the max stock capacity: %d",
        		id, quantityToIncrement));
   }

}
