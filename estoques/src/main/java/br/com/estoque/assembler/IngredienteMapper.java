package br.com.estoque.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estoque.domain.dto.IngredienteDTO;
import br.com.estoque.domain.dto.input.IngredienteInput;
import br.com.estoque.domain.model.Ingrediente;

@Component
public class IngredienteMapper {

	@Autowired
	private ModelMapper modelMapper;

	public IngredienteDTO toModel(Ingrediente estoque) {
		return modelMapper.map(estoque, IngredienteDTO.class);
	}

	public List<IngredienteDTO> toCollectionModel(List<Ingrediente> estoques) {
		return estoques.stream().map(estoque -> toModel(estoque)).collect(Collectors.toList());
	}

	public Ingrediente toDomainObject(IngredienteInput estoqueInput) {
		return modelMapper.map(estoqueInput, Ingrediente.class);
	}

}
