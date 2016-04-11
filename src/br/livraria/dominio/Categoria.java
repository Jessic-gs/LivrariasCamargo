package br.livraria.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	private int codigo;
	private String nome;
		
	@Id
	@GeneratedValue
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	
	@Column
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
