package br.uema.pecs.adotapet.resource;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uema.pecs.adotapet.model.Especie;
import br.uema.pecs.adotapet.repository.Especies;

@RestController
@RequestMapping("especies")
public class EspecieResource {

	@Autowired
	private Especies especies;

	@PostMapping
	public Especie salvar(@RequestBody Especie especie) {
		return this.especies.save(especie);
	}

	@GetMapping
	public Page<Especie> listar(Pageable pageable) {
		return this.especies.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Especie> buscar(@PathVariable Integer id) {
		Optional<Especie> optionalEspecie = especies.findById(id);

		if (!optionalEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalEspecie.get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Especie> atualizar(@PathVariable Integer id, @RequestBody Especie especie) {
		Optional<Especie> optionalEspecie = especies.findById(id);

		if (!optionalEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Especie existente = optionalEspecie.get();

		BeanUtils.copyProperties(especie, existente, "id");

		existente = especies.save(existente);

		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		Optional<Especie> optionalEspecie = especies.findById(id);

		if (!optionalEspecie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		especies.delete(optionalEspecie.get());

		return ResponseEntity.noContent().build();
	}
}
