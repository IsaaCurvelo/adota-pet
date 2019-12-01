package br.uema.pecs.adotapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uema.pecs.adotapet.model.Especie;
import br.uema.pecs.adotapet.model.Raca;

public interface Racas extends JpaRepository<Raca, Integer> {
	public List<Raca> findByEspecie(Especie especie);
}
