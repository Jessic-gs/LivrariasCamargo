package br.livraria.controle;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.livraria.dao.EditoraDao;
import br.livraria.dao.EditoraDaoImpl;
import br.livraria.dominio.Editora;

@ManagedBean
@SessionScoped
public class EditoraMB {
	
	private Editora editoraAtual;
	private List<Editora> editoras;
	
	public EditoraMB(){
		EditoraDao ediDao = new EditoraDaoImpl();
		editoraAtual = new Editora();
		try {
			editoras = ediDao.listaEditoras();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String adiciona(){
		EditoraDao ediDao = new EditoraDaoImpl();
		try {
			ediDao.adicionarEditora( editoraAtual );
			editoras = ediDao.listaEditoras();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		editoraAtual = new Editora();
		return "";
	}
	
	public void atualizar(Editora ed){
		EditoraDao ediDao = new EditoraDaoImpl();
		try {
			ediDao.adicionarEditora(ed);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String editar(Editora ed) {
		editoraAtual = ed;
		return "";
	}
	
	public String remover(Editora ed){
		EditoraDao ediDao = new EditoraDaoImpl();
		try {
			ediDao.excluirEditora(ed);
			editoras = ediDao.listaEditoras();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Editora getEditoraAtual() {
		return editoraAtual;
	}

	public void setEditoraAtual(Editora editoraAtual) {
		this.editoraAtual = editoraAtual;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}
	
}
