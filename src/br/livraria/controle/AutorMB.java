package br.livraria.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.livraria.dao.AutorDao;
import br.livraria.dao.AutorDaoImpl;
import br.livraria.dominio.Autor;

@ManagedBean
@SessionScoped
public class AutorMB {

	private Autor autorAtual;
	private List<Autor> autores;

	public AutorMB() {
		AutorDao autDao = new AutorDaoImpl();
		autorAtual = new Autor();
		autores = new ArrayList<Autor>();
		try {
			autores = autDao.listaAutores();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String adiciona() {
		AutorDao autDao = new AutorDaoImpl();
		try {
			autDao.adicionarAutor(autorAtual);
			autores = autDao.listaAutores();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		autorAtual = new Autor();
		return "";
	}

	public String cancelar() {
		autorAtual = new Autor();
		return "";
	}

	public void atualizar(Autor autor) {
		AutorDao autDao = new AutorDaoImpl();
		try {
			autDao.adicionarAutor(autor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String editar(Autor autor) {
		autorAtual = autor;
		return "";
	}

	public String remover(Autor autor) {
		AutorDao autDao = new AutorDaoImpl();
		try {
			autDao.excluirAutor(autor);
			autores = autDao.listaAutores();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Autor buscar(String autor) {
		AutorDao autDao = new AutorDaoImpl();
		try {
			autorAtual = autDao.buscaAutor(autor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autorAtual;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Autor getAutorAtual() {
		return autorAtual;
	}

	public void setAutorAtual(Autor autorAtual) {
		this.autorAtual = autorAtual;
	}
}
