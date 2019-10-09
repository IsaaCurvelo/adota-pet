package br.uema.pecs.adotapet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Adocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotBlank
	@NotNull
	private Date dataAdocao;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne
	@JoinColumn
	private Usuario adotante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAdocao() {
		return dataAdocao;
	}

	public void setDataAdocao(Date dataAdocao) {
		this.dataAdocao = dataAdocao;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Usuario getAdotante() {
		return adotante;
	}

	public void setAdotante(Usuario adotante) {
		this.adotante = adotante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adocao other = (Adocao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adocao [id=" + id + ", dataAdocao=" + dataAdocao + ", animal=" + animal + ", adotante=" + adotante
				+ "]";
	}

}
