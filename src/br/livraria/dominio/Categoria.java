package br.livraria.dominio;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Categoria implements EntityGenerica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3196691560562032386L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column
	private String nome;
	@ManyToMany(mappedBy = "categorias")
	private List<Livro> livros;
	
	/*@OneToMany(mappedBy = "categoria", targetEntity = LivroCategoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<LivroCategoria> getLivroCategorias() {
		return livroCategorias;
	}
	public void setLivroCategorias(List<LivroCategoria> livroCategorias) {
		this.livroCategorias = livroCategorias;
	}*/
	
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
