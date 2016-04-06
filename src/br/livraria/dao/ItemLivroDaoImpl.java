package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.ItemLivro;


public class ItemLivroDaoImpl implements ItemLivroDao{
	
	private static EntityManagerFactory emf;
	
	public ItemLivroDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public void inserirItemLivro(ItemLivro il) throws SQLException{
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(em.contains(il)){
			em.persist(il);
		} else {
			em.merge(il);
		}
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void excluirItemLivro(ItemLivro il) throws SQLException{
		EntityManager em = emf.createEntityManager();
		ItemLivro il1 = em.getReference(ItemLivro.class, il.getLivro());
		em.getTransaction().begin();
		em.remove(il1);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<ItemLivro> listaItemLivro() throws SQLException{
		String sql = " select  il" +
	     			 " from ItemLivro il";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<ItemLivro> qry = em.createQuery(sql, ItemLivro.class);
		List<ItemLivro> itemLivros = qry.getResultList();
		for(ItemLivro il : itemLivros){  
            System.out.println(il.getPedido()); 
		} 
		return itemLivros;
	}

}
