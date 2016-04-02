package br.livraria.controle;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.livraria.dao.CategoriaDao;
import br.livraria.dao.CategoriaDaoImpl;
import br.livraria.dominio.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaMB {
	
	private Categoria categoriaAtual;
	private List<Categoria> categorias;
	
	public CategoriaMB(){
		CategoriaDao catDao = new CategoriaDaoImpl();
		categoriaAtual = new Categoria();
		try {
			categorias = catDao.listaCategorias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String adiciona(){
		CategoriaDao catDao = new CategoriaDaoImpl();
		try {
			catDao.inserirCategoria( categoriaAtual );
			categorias = catDao.listaCategorias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		categoriaAtual = new Categoria();
		return "";
	}
	
	public void atualizar(Categoria es){
		CategoriaDao catDao = new CategoriaDaoImpl();
		try {
			catDao.inserirCategoria(es);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String editar(Categoria es) {
		categoriaAtual = es;
		return "";
	}
	
	public String remover(Categoria es){
		CategoriaDao catDao = new CategoriaDaoImpl();
		try {
			catDao.excluirCategoria(es);
			categorias = catDao.listaCategorias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Categoria getCategoriaAtual() {
		return categoriaAtual;
	}

	public void setCategoriaAtual(Categoria categoriaAtual) {
		this.categoriaAtual = categoriaAtual;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}
