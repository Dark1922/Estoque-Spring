package br.com.estoque.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.estoque.domain.dto.ProdutoDTO;
import br.com.estoque.domain.dto.input.ProdutoInput;
import br.com.estoque.domain.model.Produto;

@Component
public class ProdutoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoDTO toModel(Produto produto) {

		return modelMapper.map(produto, ProdutoDTO.class);
	}

	public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
		return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
	}

	public Produto toDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

}
