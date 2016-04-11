package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.Autor;


public class AutorDaoImpl implements AutorDao{
	
	private static EntityManagerFactory emf;
	
	public AutorDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public void adicionarAutor(Autor aut) throws SQLException{
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(em.contains(aut)){
			em.persist(aut);
		} else {
			em.merge(aut);
		}
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void editarAutor(Autor aut) throws SQLException {
		// TODO Auto-generated method stub

	}
	@Override
	public void excluirAutor(Autor aut) throws SQLException {
		EntityManager em = emf.createEntityManager();
		Autor aut1 = em.getReference(Autor.class, aut.getCodigo());
		em.getTransaction().begin();
		em.remove(aut1);
		em.getTransaction().commit();
		em.close();		
	}
	
	@Override
	public List<Autor> listaAutores() throws SQLException{
		String sql = " select sa" +
	     			 " from Autor a";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Autor> qry = em.createQuery(sql, Autor.class);
		List<Autor> autores = qry.getResultList();
		for(Autor aut : autores){  
            System.out.println(aut.getNome()); 
		} 
		return autores;
	}

}
