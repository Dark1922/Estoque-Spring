package br.com.estoque.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estoque.domain.dto.UsuarioDTO;
import br.com.estoque.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioDTO toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}

}
