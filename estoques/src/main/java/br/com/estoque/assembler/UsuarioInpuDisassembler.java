package br.com.estoque.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estoque.domain.dto.input.UsuarioInput;
import br.com.estoque.domain.model.Usuario;

@Component
public class UsuarioInpuDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
}
