package br.uema.pecs.adotapet.resource;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.uema.pecs.adotapet.model.Usuario;
import br.uema.pecs.adotapet.repository.Usuarios;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {

	@Autowired
	private Usuarios usuarios;

	@PostMapping
	public Usuario salvar(@RequestBody Usuario usuario) {
		String bcryptHashString = BCrypt.withDefaults().hashToString(10, usuario.getSenha().toCharArray());
		usuario.setSenha(bcryptHashString);
		return this.usuarios.save(usuario);
	}

	@PostMapping("/logar")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario) {
		Usuario existente = this.usuarios.findByEmail(usuario.getEmail());

		if (existente == null
				|| !BCrypt.verifyer().verify(usuario.getSenha().toCharArray(), existente.getSenha()).verified) {
			return ResponseEntity.notFound().build();
		}
		existente.setSenha(null);
		return ResponseEntity.ok(existente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Optional<Usuario> optionalUsuario = usuarios.findById(id);

		if (!optionalUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Usuario existente = optionalUsuario.get();
		String bcryptHashString = BCrypt.withDefaults().hashToString(10, usuario.getSenha().toCharArray());
		usuario.setSenha(bcryptHashString);
		BeanUtils.copyProperties(usuario, existente, "id");

		existente = usuarios.save(existente);

		return ResponseEntity.ok(existente);
	}

}
