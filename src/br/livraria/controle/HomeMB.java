package br.livraria.controle;

import java.util.ArrayList;
import java.util.List;

public class HomeMB {
	
	private List<String> imagens = new ArrayList<String>();
	
	public HomeMB(){
		imagens.add("./img/img01.jpg");
		imagens.add("./img/img02.jpg");
		imagens.add("./img/img03.jpg");
	}
	
	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	

}
