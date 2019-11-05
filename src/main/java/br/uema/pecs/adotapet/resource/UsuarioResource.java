package br.uema.pecs.adotapet.resource;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uema.pecs.adotapet.model.Usuario;
import br.uema.pecs.adotapet.repository.Usuarios;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {

	@Autowired
	private Usuarios usuarios;

	@PostMapping
	public Usuario salvar(@RequestBody Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return this.usuarios.save(usuario);
	}

	@GetMapping
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {
		BCryptPasswordEncoder bcypt = new BCryptPasswordEncoder();
		Usuario existente = this.usuarios.findByEmail(usuario.getEmail());
		if (existente == null || !bcypt.matches(usuario.getSenha(), existente.getSenha())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(existente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Optional<Usuario> optionalUsuario = usuarios.findById(id);

		if (!optionalUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario existente = optionalUsuario.get();
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		BeanUtils.copyProperties(usuario, existente, "id");

		existente = usuarios.save(existente);

		return ResponseEntity.ok(existente);
	}

}
