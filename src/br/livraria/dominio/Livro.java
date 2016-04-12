package br.livraria.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Livro {

	private int isbn;
	private String nome;
	private String sinopse;
	private String sumario;
	private Date dataPublicacao;
	
	private List<Categoria> categorias;
	
	private List<Autor> autores;
	private int tipo;
	private float preco;
	private float custo;
	private List<String> ilustracao;

	@Column(name = "sumario")
	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	@Id
	@Column(name = "isbn")
	public int getIsbn() {
		return isbn;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	@Column(name = "sinopse")
	public String getSinopse() {
		return sinopse;
	}

	@Column(name = "preco")
	public float getPreco() {
		return preco;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@ElementCollection
	@CollectionTable(name = "Ilustracoes", joinColumns = @JoinColumn(name =
	"isbn") )
	@Column(name = "ilustracao")
	public List<String> getIlustracao() {
		return ilustracao;
	}

	public void setIlustracao(List<String> ilustracao) {
		this.ilustracao = ilustracao;
	}

	@Column
	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Column
	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Autor.class)
    @JoinTable(name = "livro_autor", joinColumns = { @JoinColumn(name = "isbn") }, inverseJoinColumns = { @JoinColumn(name = "id_autor") })
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Categoria.class)
    @JoinTable(name = "livro_categoria", joinColumns = { @JoinColumn(name = "isbn") }, inverseJoinColumns = { @JoinColumn(name = "id_categoria") })
		public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Column
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
