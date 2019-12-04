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
	@NotNull
	private Date dataAdocao;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne
	@JoinColumn(name = "doador")
	private Usuario doador;

	@ManyToOne
	@JoinColumn(name = "adotante")
	private Usuario adotante;

	@Column
	@NotBlank
	@NotNull
	private String logradouro;

	@Column
	@NotBlank
	@NotNull
	private String cep;

	@Column
	@NotBlank
	@NotNull
	private String bairro;

	@Column
	@NotBlank
	@NotNull
	private String numero;

	@Column(name = "a_confirmar")
	private Boolean aConfirmar;

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

	public Usuario getDoador() {
		return doador;
	}

	public void setDoador(Usuario doador) {
		this.doador = doador;
	}

	public Usuario getAdotante() {
		return adotante;
	}

	public void setAdotante(Usuario adotante) {
		this.adotante = adotante;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getaConfirmar() {
		return aConfirmar;
	}

	public void setaConfirmar(Boolean aConfirmar) {
		this.aConfirmar = aConfirmar;
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
		return "Adocao [id=" + id + ", dataAdocao=" + dataAdocao + ", animal=" + animal + ", doador=" + doador
				+ ", adotante=" + adotante + ", logradouro=" + logradouro + ", cep=" + cep + ", bairro=" + bairro
				+ ", numero=" + numero + ", aConfirmar=" + aConfirmar + "]";
	}

}
