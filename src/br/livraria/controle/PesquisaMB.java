package br.livraria.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.livraria.dao.PesquisaDao;
import br.livraria.dao.PesquisaDaoImpl;
import br.livraria.dominio.Categoria;
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
	
	public List<Livro> pesquisa(CategoriaMB catMB, EditoraMB ediMB, AutorMB autMB){
		
		if (getTipo() == 1){
			resultado = pesquisarPorTitulo(getCampo());
		} else if (getTipo() == 2){
			autMB.setAutorAtual(autMB.buscar(getCampo()));
			resultado = pesquisarPorAutor(autMB.getAutorAtual().getId());
		} else if (getTipo() == 3){
			catMB.setCategoriaAtual(catMB.buscar(getCampo()));
			resultado = pesquisarPorCategoria(catMB.getCategoriaAtual().getId());
		} else {
			ediMB.setEditoraAtual(ediMB.buscar(getCampo()));
			resultado = pesquisarPorEditora(ediMB.getEditoraAtual().getCnpj());
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public List<Livro> pesquisarPorAutor(Long codigoAutor){
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
	
	public List<Livro> pesquisarPorCategoria(Long codigoCategoria){
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorCategoria(codigoCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		return resultado;
	}
	
	public List<Livro> pesquisarPorEditora(Integer codigoEditora){
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorEditora(codigoEditora);
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		return resultado;
	}
	
	public List<Categoria> completeTextParaCategoria(String cat, List<Categoria> catList) {
		List<Categoria> allCategorias = catList; 
        List<Categoria> filteredCategoria = new ArrayList<Categoria>();
         
        for (int i = 0; i < allCategorias.size(); i++) {
        	Categoria skin = allCategorias.get(i);
            if(skin.getNome().toLowerCase().startsWith(cat)) {
            	filteredCategoria.add(skin);
            }
        }
         
        return filteredCategoria;
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
