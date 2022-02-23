package br.com.estoque.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigModelMapper {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}
}
