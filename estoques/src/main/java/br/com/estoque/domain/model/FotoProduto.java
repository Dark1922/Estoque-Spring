package br.com.estoque.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Long id;
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;

}