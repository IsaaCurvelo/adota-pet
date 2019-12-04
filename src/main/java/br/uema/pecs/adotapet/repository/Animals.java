package br.uema.pecs.adotapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Usuario;

public interface Animals extends JpaRepository<Animal, Integer> {
	public List<Animal> findByDono(Usuario dono);
	
	@Query(value = "select a from Animal a where a.paraAdocao = true")
	public List<Animal> findAllParaAdocao();
}
