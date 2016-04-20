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
@Table(name = "livro_categoria")

public class LivroCategoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "codigo", insertable = false, updatable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "isbn", insertable = false, updatable = false)
	private Livro livro;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	@Override
	public String toString() {
		return getCategoria().getNome();
	}
}
