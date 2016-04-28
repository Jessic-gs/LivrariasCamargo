package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.Categoria;
import br.livraria.dominio.Livro;


public interface PesquisaDao {
	
	public List<Livro> pesquisarPorAutor(Long codigoAutor) throws SQLException;
	public List<Livro> pesquisarPorTitulo(String titulo) throws SQLException;
	public List<Livro> pesquisarPorCategoria(Long codigoCategoria) throws SQLException;
	
}
