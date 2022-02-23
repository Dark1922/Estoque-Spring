package br.com.estoque.domain.dto.input;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import lombok.*;

@Getter
@Setter
public class IngredienteInput {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 200)
	private String name;

	@PositiveOrZero
	@NotNull
	private BigDecimal price;

	@PositiveOrZero
	@NotNull
	private Integer quantity;

}
