package br.uema.pecs.adotapet.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Endereco;
import br.uema.pecs.adotapet.repository.Animals;
import br.uema.pecs.adotapet.repository.Enderecos;
import br.uema.pecs.adotapet.storage.Storage;

@RestController
@RequestMapping("animais")
public class AnimalResource {

	@Autowired
	private Animals animals;
	
	@Autowired
	private Enderecos enderecos;

	@Autowired
	private Storage storage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Animal salvar(@RequestParam("animal") String animalJson, @RequestParam("imagem") MultipartFile imagem) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			Animal animal = mapper.readValue(animalJson, Animal.class);
			
			Endereco endereco = this.enderecos.save(animal.getEndereco());
			animal.setEndereco(endereco);
			
			
			animal.setFoto("/img/");
			animal = this.animals.save(animal);
			
			animal.setFoto(animal.getFoto() + animal.getId() + ".jpg");
			this.animals.save(animal);

			storage.salvarFoto(imagem, animal.getId() + ".jpg");

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

}
