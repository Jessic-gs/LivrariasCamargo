package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.Editora;


public class EditoraDaoImpl implements EditoraDao{
	
	private static EntityManagerFactory emf;
	
	public EditoraDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public void adicionarEditora(Editora ed) throws SQLException{
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(em.contains(ed)){
			em.persist(ed);
		} else {
			em.merge(ed);
		}
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void excluirEditora(Editora ed) throws SQLException{
		EntityManager em = emf.createEntityManager();
		Editora ed1 = em.getReference(Editora.class, ed.getCnpj());
		em.getTransaction().begin();
		em.remove(ed1);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Editora> listaEditoras() throws SQLException{
		String sql = " select  e" +
	     			 " from Editora e";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Editora> qry = em.createQuery(sql, Editora.class);
		List<Editora> editoras = qry.getResultList();
		for(Editora ed : editoras){  
            System.out.println(ed.getNome()); 
		} 
		return editoras;
	}

	@Override
	public void editarEditora(Editora ed) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
