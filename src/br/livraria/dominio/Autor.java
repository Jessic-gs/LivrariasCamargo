package br.livraria.dominio;

import java.util.Date;

public class Autor {
	private String nome;
	private String nascimento;
	private String biografia;
	private Date dataNascimento;
	private Date dataFalecimento;
	private String localNascimento;
	private String localFalecimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataFalecimento() {
		return dataFalecimento;
	}
	public void setDataFalecimento(Date dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}
	public String getLocalNascimento() {
		return localNascimento;
	}
	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}
	public String getLocalFalecimento() {
		return localFalecimento;
	}
	public void setLocalFalecimento(String localFalecimento) {
		this.localFalecimento = localFalecimento;
	}
}
