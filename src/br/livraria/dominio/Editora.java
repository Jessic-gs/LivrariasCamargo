package br.livraria.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Editora {

	private long cnpj;
	private String nome;
	private String endereco;
	private String telefone;

	@Id
	public long getCnpj() {
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

	public void setCnpj(long cnpj) {
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

	@Override
	public String toString() {
		return getNome();
	}
}
