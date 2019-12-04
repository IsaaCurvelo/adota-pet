package br.uema.pecs.adotapet.resource;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Usuario;
import br.uema.pecs.adotapet.repository.Animals;
import br.uema.pecs.adotapet.repository.Usuarios;
import br.uema.pecs.adotapet.storage.Storage;

@RestController
@RequestMapping("animais")
public class AnimalResource {

	@Autowired
	private Animals animals;

	@Autowired
	private Usuarios usuarios;

	@Autowired
	private Storage storage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Animal salvar(@RequestParam("animal") String animalJson, @RequestParam("imagem") MultipartFile imagem) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			Animal animal = mapper.readValue(animalJson, Animal.class);
			animal.setDataCadastro(new Date());
			animal.setFoto("/fotos/");
			animal = this.animals.save(animal);

			animal.setFoto(animal.getFoto() + animal.getId() + ".jpg");
			this.animals.save(animal);

			storage.salvarFoto(imagem, animal.getId() + ".jpg");

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> atualizar(@PathVariable Integer id, @RequestBody Animal animal) {
		Optional<Animal> optionalAnimal = animals.findById(id);

		if (!optionalAnimal.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Animal existente = optionalAnimal.get();

		BeanUtils.copyProperties(animal, existente, "id");

		return ResponseEntity.ok(animals.save(existente));
	}

	@GetMapping
	public List<Animal> listar() {
		return this.animals.findAllParaAdocao();
	}

	@GetMapping("/usuario/{usuarioId}")
	public List<Animal> listarPorUsuario(@PathVariable("usuarioId") Integer usuarioId) {
		Usuario usuario = usuarios.findById(usuarioId).get();
		return this.animals.findByDono(usuario);
	}

}
