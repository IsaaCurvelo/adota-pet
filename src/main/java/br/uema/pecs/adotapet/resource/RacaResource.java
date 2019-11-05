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

import br.uema.pecs.adotapet.model.Raca;
import br.uema.pecs.adotapet.repository.Racas;


@RestController
@RequestMapping("racas")
public class RacaResource {

	@Autowired
	private Racas racas;

	@PostMapping
	public Raca salvar(@RequestBody Raca raca) {
		return this.racas.save(raca);
	}

	@GetMapping
	public Page<Raca> listar(Pageable pageable) {
		return this.racas.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Raca> buscar(@PathVariable Integer id) {
		Optional<Raca> optionalRaca = racas.findById(id);

		if (!optionalRaca.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalRaca.get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Raca> atualizar(@PathVariable Integer id, @RequestBody Raca raca) {
		Optional<Raca> optionalRaca = racas.findById(id);

		if (!optionalRaca.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Raca existente = optionalRaca.get();

		BeanUtils.copyProperties(raca, existente, "id");

		existente = racas.save(existente);

		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		Optional<Raca> optionalRaca = racas.findById(id);

		if (!optionalRaca.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		racas.delete(optionalRaca.get());

		return ResponseEntity.noContent().build();
	}
}
