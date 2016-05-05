package br.livraria.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Editora implements EntityGenerica {

	private static final long serialVersionUID = -6242124539409979554L;
	private long id;
	private String nome;
	private String endereco;
	private String telefone;

	@Id
	@Column(name = "cnpj")
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Column
	public String getNome() {
		return nome;
	}

	@Column
	public String getEndereco() {
		return endereco;
	}

	@Column
	public String getTelefone() {
		return telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return getNome();
	}

}
