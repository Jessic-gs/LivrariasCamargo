package br.livraria.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Categoria {
	
	private int codigo;
	private String nome;
	private List<Livro> livros;
		
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
	@Override
	public String toString() {
		return getNome();
	}
	
	@ManyToMany
    @JoinTable(name = "livro_categoria", joinColumns = { @JoinColumn(name = "codigo") }, inverseJoinColumns = { @JoinColumn(name = "isbn") })
	public List<Livro> getLivros() {
		return livros;
	}
	
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}
