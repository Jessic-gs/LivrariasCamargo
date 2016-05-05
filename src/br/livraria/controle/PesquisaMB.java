package br.livraria.controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.livraria.dao.PesquisaDao;
import br.livraria.dao.PesquisaDaoImpl;
import br.livraria.dominio.Livro;
import de.larmic.butterfaces.model.text.AutoCompleteModel;

@ManagedBean
@SessionScoped
public class PesquisaMB implements Serializable, AutoCompleteModel {

	private List<Livro> resultado;
	private Integer tipo;
	private String campo;

	public PesquisaMB() {
		PesquisaDao pDao = new PesquisaDaoImpl();
		resultado = new ArrayList<Livro>();
	}

	@Override
	public List<String> autoComplete(Object value) {
		List<String> lista = new ArrayList<String>();

		AutorMB autMB = new AutorMB();
		for (int i = 0; i < autMB.getAutores().size(); i++) {
			lista.add(autMB.getAutores().get(i).getNome());
		}

		CategoriaMB catMB = new CategoriaMB();
		for (int i = 0; i < catMB.getCategorias().size(); i++) {
			lista.add(catMB.getCategorias().get(i).getNome());
		}

		EditoraMB ediMB = new EditoraMB();
		for (int i = 0; i < ediMB.getEditoras().size(); i++) {
			lista.add(ediMB.getEditoras().get(i).getNome());
		}

		return lista;
	}

	public List<Livro> pesquisa(CategoriaMB catMB, EditoraMB ediMB, AutorMB autMB) {
		if (getTipo() == 1) {
			resultado = pesquisarPorTitulo(getCampo());
		} else if (getTipo() == 2) {
			autMB.setAutorAtual(autMB.buscar(getCampo()));
			resultado = pesquisarPorAutor(autMB.getAutorAtual().getId());
		} else if (getTipo() == 3) {
			catMB.setCategoriaAtual(catMB.buscar(getCampo()));
			resultado = pesquisarPorCategoria(catMB.getCategoriaAtual().getId());
		} else {
			ediMB.setEditoraAtual(ediMB.buscar(getCampo()));
			resultado = pesquisarPorEditora(ediMB.getEditoraAtual().getId());
		}

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pesquisa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public List<Livro> pesquisarPorAutor(Long codigoAutor) {
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorAutor(codigoAutor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Livro> pesquisarPorTitulo(String titulo) {
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorTitulo(titulo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Livro> pesquisarPorCategoria(Long codigoCategoria) {
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorCategoria(codigoCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Livro> pesquisarPorEditora(Long codigoEditora) {
		PesquisaDao pDao = new PesquisaDaoImpl();
		try {
			resultado = pDao.pesquisarPorEditora(codigoEditora);
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
