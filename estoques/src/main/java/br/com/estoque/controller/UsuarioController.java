package br.com.estoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.core.security.JWTTokenAutenticacaoService;
import br.com.estoque.core.security.dto.JWTAuthResponseDTO;
import br.com.estoque.domain.dto.LoginDTO;
import br.com.estoque.domain.dto.UsuarioDTO;
import br.com.estoque.domain.dto.input.UsuarioInput;
import br.com.estoque.domain.service.UsuarioService;

@RestController
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {
		return ResponseEntity.ok().body(usuarioService.save(usuarioInput));
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenAutenticacaoService jw;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.jw.generateToken(authentication);

		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}
}
