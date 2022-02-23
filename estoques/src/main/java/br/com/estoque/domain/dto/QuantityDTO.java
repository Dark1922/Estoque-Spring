package br.com.estoque.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityDTO {

	@PositiveOrZero
	@NotNull
	private Integer quantity;
}