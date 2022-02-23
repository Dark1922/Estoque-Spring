package br.com.estoque.domain.dto;

import java.math.BigDecimal;

import br.com.estoque.domain.model.FotoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

	private Long id;

	private String name;

	private BigDecimal price;

	private FotoProduto fotoProduto;

}
