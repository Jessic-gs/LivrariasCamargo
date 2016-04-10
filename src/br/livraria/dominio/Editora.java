package br.livraria.dominio;


public class Editora {
	
	private int cnpj;
	private String nome;
	private String endereco;
	private String telefone;
		
	public int getCnpj() {
		return cnpj;
	}
	public String getNome() {
		return nome;
	}
	public String getEndereco(){
		return endereco;
	}
	public String getTelefone(){
		return telefone;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(String endereco){
		this.endereco = endereco;
	}
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
		
}
