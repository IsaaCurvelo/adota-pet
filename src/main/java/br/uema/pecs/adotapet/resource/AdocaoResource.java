package br.uema.pecs.adotapet.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uema.pecs.adotapet.model.Adocao;
import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.repository.Adocaos;
import br.uema.pecs.adotapet.repository.Animals;

@RestController
@RequestMapping("adocaos")
public class AdocaoResource {

	@Autowired
	private Adocaos adocaos;

	@Autowired
	private Animals animals;

	@PostMapping
	public Adocao salvar(@RequestBody Adocao adocao) {
		adocao.setDataAdocao(new Date());
		adocao.setaConfirmar(true);
		return this.adocaos.save(adocao);
	}

	@PutMapping("/confirmar/{adocaoId}")
	public ResponseEntity<Void> confirmarAdocao(@PathVariable Integer adocaoId) {
		Adocao adocao = adocaos.findById(adocaoId).get();
		Animal animalExistente = this.animals.findById(adocao.getAnimal().getId()).get();

		animalExistente.setLogradouro(adocao.getLogradouro());
		animalExistente.setCep(adocao.getCep());
		animalExistente.setBairro(adocao.getBairro());
		animalExistente.setNumero(adocao.getNumero());
		animalExistente.setDono(adocao.getAdotante());
		animalExistente.setParaAdocao(false);
		this.animals.save(animalExistente);

		adocao.setaConfirmar(false);
		adocao.setDataAdocao(new Date());
		this.adocaos.save(adocao);

		return ResponseEntity.noContent().build();
	}

}
