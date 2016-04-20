package br.livraria.dominio;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Livro {

	private int isbn;
	private String nome;
	private String sinopse;
	private String sumario;
	private Date dataPublicacao;
	private List<LivroCategoria> livroCategorias = new ArrayList<LivroCategoria>();
	private List<LivroAutores> livroAutores= new ArrayList<LivroAutores>();
	private Editora editora;
	private int tipo;
	private float preco;
	private float custo;
	private List<String> ilustracao;

	
	@Id
	@Column(name = "isbn")
	public int getIsbn() {
		return isbn;
	}
	@Column(name = "sumario")
	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
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
	//@ManyToMany
    //@JoinTable(name = "livro_categoria", joinColumns = { @JoinColumn(name = "isbn") }, inverseJoinColumns = { @JoinColumn(name = "codigo") })
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "livro", targetEntity = LivroCategoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<LivroCategoria> getLivroCategorias() {
		return livroCategorias;
	}
	public void setLivroCategorias(List<LivroCategoria> livroCategorias) {
		this.livroCategorias = livroCategorias;
	}

	@Column
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@ManyToOne(targetEntity=Editora.class)
	@JoinColumn(name="id_editora")
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	@OneToMany(mappedBy = "livro", targetEntity = LivroAutores.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<LivroAutores> getLivroAutores() {
		return livroAutores;
	}
	public void setLivroAutores(List<LivroAutores> livroAutores) {
		this.livroAutores = livroAutores;
	}
}
