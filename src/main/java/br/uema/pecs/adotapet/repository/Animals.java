package br.uema.pecs.adotapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uema.pecs.adotapet.model.Animal;

public interface Animals extends JpaRepository<Animal, Integer> {

}
