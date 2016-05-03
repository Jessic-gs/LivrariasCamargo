package br.livraria.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Livro implements EntityGenerica {
	
	private static final long serialVersionUID = -3944201228532106574L;
	
	@Id
	@Column(name = "isbn")
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sinopse")
	private String sinopse;
	
	@Column(name = "sumario")
	private String sumario;
	
	@Column
	private Date dataPublicacao;
	
	@ManyToMany
	@JoinTable(name = "livro_categoria", joinColumns = @JoinColumn(name = "livro_isbn") , inverseJoinColumns = @JoinColumn(name = "categoria_codigo") )
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@ManyToMany
	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "livro_isbn") , inverseJoinColumns = @JoinColumn(name = "autor_codigo") )
	private List<Autor> autores = new ArrayList<Autor>();

	@ManyToOne(targetEntity = Editora.class)
	@JoinColumn(name = "id_editora")
	private Editora editora;
	
	@Column
	private int tipo;
	
	@Column(name = "preco")
	private float preco;

	@Column
	private int quatidadeEstoque;
	
	@Column
	private int quatidadePaginas;
	
	@Column
	private float custo;

	@ElementCollection
	@CollectionTable(name = "Ilustracoes", joinColumns = @JoinColumn(name = "isbn") )
	@Column(name = "ilustracao")
	private List<String> ilustracao;


	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	public String getNome() {
		return nome;
	}

	public String getSinopse() {
		return sinopse;
	}

	public float getPreco() {
		return preco;
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

	public List<String> getIlustracao() {
		return ilustracao;
	}

	public void setIlustracao(List<String> ilustracao) {
		this.ilustracao = ilustracao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public float getCusto() {
		return custo;
	}
	public int getQuatidadeEstoque() {
		return quatidadeEstoque;
	}

	public void setQuatidadeEstoque(int quatidadeEstoque) {
		this.quatidadeEstoque = quatidadeEstoque;
	}

	public int getQuatidadePaginas() {
		return quatidadePaginas;
	}

	public void setQuatidadePaginas(int quatidadePaginas) {
		this.quatidadePaginas = quatidadePaginas;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;		
	}
}
