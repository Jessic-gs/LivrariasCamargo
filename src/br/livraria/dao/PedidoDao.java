package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.livraria.dominio.Pedido;

public interface PedidoDao {
	
	public void inserirPedido(Pedido p) throws SQLException;
	public void excluirPedido(Pedido p) throws SQLException;
	public List<Pedido> listaPedido() throws SQLException;
	
}
