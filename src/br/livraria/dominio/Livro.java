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
import javax.persistence.OneToMany;


public class Livro {

	private int insb;
	private String nome;
	private String sinopse;
	private String sumario;
	private Date dataPublicacao;
	private List<Categoria> categorias;
	private List<Autor> autores;
	private int tipoLivro;
	private float preco;
	private double custo;
	private List<String> ilustracao;

	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	@Id
	@Column(name="isbn")
	public int getInsb() {
		return insb;
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

	public void setInsb(int insb) {
		this.insb = insb;
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
	@CollectionTable(name="Ilustracoes", joinColumns= @JoinColumn(name="isbn"))
	@Column(name="ilustracao")
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
	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	@OneToMany(mappedBy = "livro", targetEntity = Autor.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@OneToMany(mappedBy = "livro", targetEntity = Categoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Column
	public int getTipoLivro() {
		return tipoLivro;
	}

	public void setTipoLivro(int tipoLivro) {
		this.tipoLivro = tipoLivro;
	}

}
