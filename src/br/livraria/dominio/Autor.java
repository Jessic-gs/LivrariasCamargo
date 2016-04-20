package br.livraria.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {
	private int codigo;
	private String nome;
	private String biografia;
	private Date dataNascimento;
	private Date dataFalecimento;
	private String localNascimento;
	private String localFalecimento;
	private List<LivroAutores> livroAutores= new ArrayList<LivroAutores>();

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
	@Id
	@GeneratedValue
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return getNome();
	}

	@OneToMany(mappedBy = "autor", targetEntity = LivroAutores.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<LivroAutores> getLivroAutores() {
		return livroAutores;
	}

	public void setLivroAutores(List<LivroAutores> livroAutores) {
		this.livroAutores = livroAutores;
	}
}
