package br.livraria.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Categoria {
	
	private int codigo;
	private String nome;
	private List<LivroCategoria> livroCategorias = new ArrayList<LivroCategoria>();;
	@Id
	@GeneratedValue
	@Column
	public int getCodigo() {
		return codigo;
	}
	@Column
	public String getNome() {
		return nome;
	}
	@OneToMany(mappedBy = "categoria", targetEntity = LivroCategoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<LivroCategoria> getLivroCategorias() {
		return livroCategorias;
	}
	public void setLivroCategorias(List<LivroCategoria> livroCategorias) {
		this.livroCategorias = livroCategorias;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return getNome();
	}

}
