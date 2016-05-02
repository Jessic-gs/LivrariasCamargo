package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.Autor;


public interface AutorDao {

	public void adicionarAutor(Autor aut) throws SQLException;
	public void editarAutor(Autor aut) throws SQLException;
	public List<Autor> listaAutores() throws SQLException;
	public void excluirAutor(Autor aut) throws SQLException;
	public Autor buscaAutor(String autor) throws SQLException;
}


