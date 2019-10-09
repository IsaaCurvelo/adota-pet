package br.uema.pecs.adotapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uema.pecs.adotapet.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
}
