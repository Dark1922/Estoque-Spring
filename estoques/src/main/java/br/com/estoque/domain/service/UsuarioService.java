package br.com.estoque.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estoque.assembler.UsuarioInpuDisassembler;
import br.com.estoque.assembler.UsuarioModelAssembler;
import br.com.estoque.domain.dto.UsuarioDTO;
import br.com.estoque.domain.dto.input.UsuarioInput;
import br.com.estoque.domain.exception.NegocioException;
import br.com.estoque.domain.exception.UsuarioNotFoundException;
import br.com.estoque.domain.model.Usuario;
import br.com.estoque.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final String MSG_USUARIO_NAO_ENCOTNADA = "Não existe um cadastro de Usuario com código %d";

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInpuDisassembler usuarioInpuDisassembler;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public UsuarioDTO save(UsuarioInput usuarioInput) {

		validarEmail(usuarioInput);

		Usuario usuarioAtual = usuarioInpuDisassembler.toDomainObject(usuarioInput);

		/* Criptografia de senha */
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioAtual.getPassword());
		usuarioAtual.setPassword(senhaCriptografada);

		return usuarioModelAssembler.toModel(usuarioRepository.save(usuarioAtual));
	}

	public void validarEmail(UsuarioInput usuarioInput) {
		boolean usuarioExistente = usuarioRepository.findByLogin(usuarioInput.getLogin()) != null;
		if (usuarioExistente) {
			throw new NegocioException(String.format("Já existe um usuário com este e-mail"));
		}
	}

	public Usuario buscarOuFalhar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(String.format(MSG_USUARIO_NAO_ENCOTNADA, id)));
	}

}
