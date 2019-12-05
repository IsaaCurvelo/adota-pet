package br.uema.pecs.adotapet.resource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uema.pecs.adotapet.model.Adocao;
import br.uema.pecs.adotapet.model.Animal;
import br.uema.pecs.adotapet.model.Usuario;
import br.uema.pecs.adotapet.repository.Adocaos;
import br.uema.pecs.adotapet.repository.Animals;
import br.uema.pecs.adotapet.repository.Usuarios;

@RestController
@RequestMapping("adocaos")
public class AdocaoResource {

	@Autowired
	private Adocaos adocaos;

	@Autowired
	private Animals animals;

	@Autowired
	private Usuarios usuarios;

	@GetMapping("/usuario/{usuarioId}")
	public List<Adocao> adocoesaConfirmar(@PathVariable Integer usuarioId) {
		Usuario usuario = this.usuarios.findById(usuarioId).get();
		return this.adocaos.findAdocaoPendentePorUsuario(usuario);
	}

	@PostMapping
	public Adocao salvar(@RequestBody Adocao adocao) {
		adocao.setDataAdocao(new Date());
		adocao.setaConfirmar(true);
		return this.adocaos.save(adocao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		Optional<Adocao> optionalAdocao = adocaos.findById(id);

		if (!optionalAdocao.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		adocaos.delete(optionalAdocao.get());

		return ResponseEntity.noContent().build();
	}

	@Transactional
	@PutMapping("/confirmar/{adocaoId}")
	public List<Adocao> confirmarAdocao(@PathVariable Integer adocaoId) {
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
		this.adocaos.deletaAdocaoPendenteAnimal(adocao.getAnimal());

		return this.adocaos.findAdocaoPendentePorUsuario(adocao.getDoador());
	}

}
