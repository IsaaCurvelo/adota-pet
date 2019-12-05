package br.uema.pecs.adotapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.uema.pecs.adotapet.model.Adocao;
import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Usuario;

public interface Adocaos extends JpaRepository<Adocao, Integer> {
	@Query(value = "select a from Adocao a where a.doador = :usuario and a.aConfirmar = true")
	public List<Adocao> findAdocaoPendentePorUsuario(@Param("usuario") Usuario usuario);
	
	@Modifying
	
	@Query(value = "delete from Adocao a where a.animal = :animal and a.aConfirmar = true")
	public void deletaAdocaoPendenteAnimal(@Param("animal") Animal animal);
}
