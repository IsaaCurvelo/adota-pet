package br.uema.pecs.adotapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uema.pecs.adotapet.model.Especie;

public interface Especies extends JpaRepository<Especie, Integer>{

}
