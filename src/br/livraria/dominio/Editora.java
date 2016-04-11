package br.livraria.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Editora {

	private int cnpj;
	private String nome;
	private String endereco;
	private String telefone;

	@Id
	@GeneratedValue
	public int getCnpj() {
		return cnpj;
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

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
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

}
