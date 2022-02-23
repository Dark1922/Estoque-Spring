package br.com.estoque.domain.exception;

public class UsuarioNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String mensagem) {
		super(mensagem);
	}

}
