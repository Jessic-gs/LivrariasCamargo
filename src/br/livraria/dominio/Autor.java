package br.livraria.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Autor implements EntityGenerica {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5041525618862980362L;
	private long id;
	private String nome;
	private int falecido;
	private String biografia;
	private Date dataNascimento;
	private Date dataFalecimento;
	private String localNascimento;
	private String localFalecimento;
	private List<Livro> livros;

	@Id
	@GeneratedValue
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Column
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column
	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	@Column
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column
	public Date getDataFalecimento() {
		return dataFalecimento;
	}

	public void setDataFalecimento(Date dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}

	@Column
	public String getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}

	@Column
	public String getLocalFalecimento() {
		return localFalecimento;
	}

	public void setLocalFalecimento(String localFalecimento) {
		this.localFalecimento = localFalecimento;
	}

	@Override
	public String toString() {
		return getNome();
	}

	@ManyToMany(mappedBy = "autores")
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	@Column
	public int getFalecido() {
		return falecido;
	}

	public void setFalecido(int falecido) {
		this.falecido = falecido;
	}

}
