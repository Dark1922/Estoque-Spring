package br.com.estoque.domain.exception;

public class ProductCannotBeSoldException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductCannotBeSoldException(Long id, int quantityToDecrement) {
        super(String.format("Produto with %d does not have the required amount of ingredients for sale %d",
                id, quantityToDecrement));
    }
}
