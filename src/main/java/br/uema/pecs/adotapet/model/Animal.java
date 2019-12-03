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
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotNull
	private String nome;

	@Column
	@NotNull
	private Integer idade;

	@NotNull
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column
	@NotNull
	private String sexo;

	@Column
	@NotNull
	private String foto;

	@ManyToOne
	@JoinColumn(name = "raca_id")
	private Raca raca;

	@ManyToOne
	@JoinColumn(name = "doador")
	private Usuario doador;

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

	@NotNull
	@Column(name = "para_adocao")
	private Boolean paraAdocao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Usuario getDoador() {
		return doador;
	}

	public void setDoador(Usuario doador) {
		this.doador = doador;
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

	public Boolean getParaAdocao() {
		return paraAdocao;
	}

	public void setParaAdocao(Boolean paraAdocao) {
		this.paraAdocao = paraAdocao;
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
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nome=" + nome + ", idade=" + idade + ", dataCadastro=" + dataCadastro + ", sexo="
				+ sexo + ", foto=" + foto + ", raca=" + raca + ", doador=" + doador + ", logradouro=" + logradouro
				+ ", cep=" + cep + ", bairro=" + bairro + ", numero=" + numero + ", paraAdocao=" + paraAdocao + "]";
	}

}
