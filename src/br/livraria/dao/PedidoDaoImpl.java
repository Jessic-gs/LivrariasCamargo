package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.Pedido;


public class PedidoDaoImpl implements PedidoDao{
	
	private static EntityManagerFactory emf;
	
	public PedidoDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public void inserirPedido(Pedido p) throws SQLException{
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(em.contains(p)){
			em.persist(p);
		} else {
			em.merge(p);
		}
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void excluirPedido(Pedido p) throws SQLException{
		EntityManager em = emf.createEntityManager();
		Pedido p1 = em.getReference(Pedido.class, p.getNumero());
		em.getTransaction().begin();
		em.remove(p1);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Pedido> listaPedido() throws SQLException{
		String sql = " select  p" +
	     			 " from Pedido p";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Pedido> qry = em.createQuery(sql, Pedido.class);
		List<Pedido> pedidos = qry.getResultList();
		for(Pedido p : pedidos){  
            System.out.println(p.getNumero()); 
		} 
		return pedidos;
	}

}
