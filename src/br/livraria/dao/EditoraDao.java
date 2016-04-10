package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.Editora;


public interface EditoraDao {
	
	public void adicionarEditora(Editora ed) throws SQLException;
	public void editarEditora(Editora ed) throws SQLException;
	public List<Editora> listaEditoras() throws SQLException;
	public void excluirEditora(Editora ed) throws SQLException;
	
}
