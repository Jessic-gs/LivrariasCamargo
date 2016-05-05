package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.Autor;
import br.livraria.dominio.Categoria;
import br.livraria.dominio.Livro;


public class CategoriaDaoImpl implements CategoriaDao{
	
	private static EntityManagerFactory emf;
	
	public CategoriaDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public void inserirCategoria(Categoria cat) throws SQLException{
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(em.contains(cat)){
			em.persist(cat);
		} else {
			em.merge(cat);
		}
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void excluirCategoria(Categoria cat) throws SQLException{
		EntityManager em = emf.createEntityManager();
		Categoria cat1 = em.getReference(Categoria.class, cat.getId());
		em.getTransaction().begin();
		em.remove(cat1);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Categoria> listaCategorias() throws SQLException{
		String sql = " select  c" +
	     			 " from Categoria c";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Categoria> qry = em.createQuery(sql, Categoria.class);
		List<Categoria> categorias = qry.getResultList();
		for(Categoria cat : categorias){  
            System.out.println(cat.getNome()); 
		} 
		return categorias;
	}
	
	@Override
	public Categoria buscaCategoria(String cat) throws SQLException{
		String sql = " select  c" +
	    			 " from Categoria c" + 
	    			 " where c.nome = :cat";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Categoria> qry = em.createQuery(sql, Categoria.class).setParameter("cat", cat);
		Categoria resultado = new Categoria(); 
		try {
			resultado = qry.getSingleResult();
		} catch (Exception e) {
			resultado = new Categoria();
		}
		return resultado;
	}

}
