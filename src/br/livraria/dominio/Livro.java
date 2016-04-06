package br.livraria.dominio;

import java.util.List;

public class Livro {
	
	private int insb;
	private String nome;
	private String sinopse;
	private float preco;
	private List<String> ilustracao;
	
	public int getInsb() {
		return insb;
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

	public List<String> getIlustracao() {
		return ilustracao;
	}
	public void setIlustracao(List<String> ilustracao) {
		this.ilustracao = ilustracao;
	}
	
	
}
