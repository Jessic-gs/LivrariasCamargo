package br.livraria.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.type.IntegerType;

import br.livraria.dao.PesquisaDao;
import br.livraria.dao.PesquisaDaoImpl;
import br.livraria.dominio.Livro;

@ManagedBean
@SessionScoped
public class PesquisaMB {
	
	private List<Livro> resultado;
	private Integer tipo;
	private String campo;
	
	public PesquisaMB(){
		PesquisaDao pDao = new PesquisaDaoImpl();
		resultado = new ArrayList<Livro>();
	}
	
	public List<Livro> pesquisa(){
		
		if (getTipo() == 1){
			resultado = pesquisarPorTitulo(getCampo());
		} else if (getTipo() == 2){
			resultado = pesquisarPorAutor(Integer.parseInt(getCampo()));
		} else {
			resultado = pesquisarPorCategoria(Integer.parseInt(getCampo()));
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public List<Livro> pesquisarPorAutor(Integer codigoAutor){
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorAutor(codigoAutor);
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		return resultado;
	}
	
	public List<Livro> pesquisarPorTitulo(String titulo){
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorTitulo(titulo);
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		return resultado;
	}
	
	public List<Livro> pesquisarPorCategoria(Integer codigoCategoria){
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorCategoria(codigoCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		return resultado;
	}


	public List<Livro> getResultado() {
		return resultado;
	}

	public void setResultado(List<Livro> resultado) {
		this.resultado = resultado;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	
}
