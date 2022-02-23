package br.com.estoque.domain.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.estoque.domain.model.FotoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private BigDecimal price;

	private FotoProduto fotoProduto;

}
