package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.Categoria;


public interface CategoriaDao {
	
	public void inserirCategoria(Categoria es) throws SQLException;
	public void excluirCategoria(Categoria es) throws SQLException;
	public List<Categoria> listaCategorias() throws SQLException;
	
}
