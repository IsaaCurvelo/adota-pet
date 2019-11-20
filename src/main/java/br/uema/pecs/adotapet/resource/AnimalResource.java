package br.uema.pecs.adotapet.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.repository.Animals;
import br.uema.pecs.adotapet.storage.Storage;

@RestController
@RequestMapping("animais")
public class AnimalResource {

	@Autowired
	private Animals animals;

	@Autowired
	private Storage storage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Animal salvar(@RequestParam("animal") String animalJson, @RequestParam("imagem") MultipartFile imagem) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			Animal animal = mapper.readValue(animalJson, Animal.class);

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

	@GetMapping
	public Page<Animal> listar(Pageable pageable) {
		return this.animals.findAll(pageable);
	}

}
