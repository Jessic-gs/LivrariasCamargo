package br.livraria.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livro_autor")
public class LivroAutores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "codigo", insertable = false, updatable = false)
	private Autor autor;
	
	
	@ManyToOne
	@JoinColumn(name = "isbn", insertable = false, updatable = false)
	private Livro livro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getCodigo() {
		return id;
	}
	public void setCodigo(int id) {
		this.id = id;
	}
}
