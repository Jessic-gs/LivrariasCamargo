package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.livraria.dominio.Livro;


public class PesquisaDaoImpl implements PesquisaDao{
	
	private static EntityManagerFactory emf;
	
	public PesquisaDaoImpl(){
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}
	
	@Override
	public List<Livro> pesquisarPorAutor(Integer codigoAutor) throws SQLException{
		String sql = " select  l" +
	     			 " from Livro" +
	     			 " LEFT JOIN FETCH livro.categoria" +
	     			 " where livro_categoria.cadigo like :codigo";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Livro> qry = em.createQuery(sql, Livro.class).setParameter("codigo", codigoAutor);
		List<Livro> resultado = qry.getResultList();
		for(Livro l : resultado){  
	       System.out.println(l); 
		}
		return resultado;
	}
	
	@Override
	public List<Livro> pesquisarPorTitulo(String titulo) throws SQLException{
		String sql = " select  l" +
	    			 " from Livro l" + 
	    			 " where l.nome like :titulo";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Livro> qry = em.createQuery(sql, Livro.class).setParameter("titulo", "%"+titulo+"%");
		List<Livro> resultado = qry.getResultList();
		for(Livro l : resultado){  
	       System.out.println(l); 
		}	
		return resultado;
	}

	@Override
	public List<Livro> pesquisarPorCategoria(Integer codigoCategoria) throws SQLException{
		String sql = " select  l" +
	     			 " from Livro" +
	     			 " LEFT JOIN FETCH livro.categoria" +
	     			 " where livro_categoria.cadigo like :codigo";
		
		EntityManager em = emf.createEntityManager();
		TypedQuery<Livro> qry = em.createQuery(sql, Livro.class).setParameter("codigo", codigoCategoria);
		List<Livro> resultado = qry.getResultList();
		for(Livro l : resultado){  
            System.out.println(l); 
		} 
		
		return resultado;
	}

}
