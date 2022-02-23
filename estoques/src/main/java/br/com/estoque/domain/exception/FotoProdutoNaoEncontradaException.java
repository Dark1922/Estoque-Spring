package br.com.estoque.domain.exception;

public class FotoProdutoNaoEncontradaException extends Exception {
	
	 private static final long serialVersionUID = 1L;

	    public FotoProdutoNaoEncontradaException(String mensagem) {
	        super(mensagem);
	    }

	    public FotoProdutoNaoEncontradaException(Long produtoId) {
	        this(String.format("Não existe um cadastro de foto do produto com código %d ",
	                produtoId));
	    }
}
