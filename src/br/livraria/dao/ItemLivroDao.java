package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.ItemLivro;

public interface ItemLivroDao {
	
	public void inserirItemLivro(ItemLivro il) throws SQLException;
	public void excluirItemLivro(ItemLivro il) throws SQLException;
	public List<ItemLivro> listaItemLivro() throws SQLException;
	
}
