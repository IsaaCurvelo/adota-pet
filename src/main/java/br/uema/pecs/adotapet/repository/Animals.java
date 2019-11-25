package br.uema.pecs.adotapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Usuario;

public interface Animals extends JpaRepository<Animal, Integer> {
	public List<Animal> findByDoador(Usuario usuario);
}
