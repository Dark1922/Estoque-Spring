package br.com.estoque.domain.exception;

public class EntityNotFoundException extends NegocioException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}

}