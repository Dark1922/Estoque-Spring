package br.com.estoque.domain.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estoque.domain.model.Usuario;
import br.com.estoque.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsuarioRepository usuarioRepository;

	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado"); // username não encontrado
		}

		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}

	public void insereAcessoPadrão(Long id) {

		String constraint = usuarioRepository.consultarConstraintRole();

		if (constraint != null) {

			jdbcTemplate.execute("alter table usuarios_role DROP CONSTRAINT " + constraint);

			usuarioRepository.insereAcessoRolePadrao(id);
		}
	}
}