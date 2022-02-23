package br.com.estoque.domain.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
public class IngredienteDTO {

	private Long id;

	private String name;

	private BigDecimal price;

	private Integer quantity;

}
