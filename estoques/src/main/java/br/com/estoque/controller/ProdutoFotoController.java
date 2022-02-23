package br.com.estoque.controller;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.estoque.domain.dto.input.FotoProdutoInput;

@RestController
@RequestMapping("/produtos/{produtoId}/foto")
public class ProdutoFotoController {

	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public void atualizar(@PathVariable Long produtoId, FotoProdutoInput fotoProdutoInput,
			@RequestParam MultipartFile arquivo) throws Exception {
		
		var nomrArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
		var arquivoFoto = Path.of("Imagens");
		fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
		
		System.out.println(fotoProdutoInput.getDescricao());
		System.out.println(fotoProdutoInput.getArquivo().getContentType());
		System.out.println(nomrArquivo);
		
		fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
	}


	
}
