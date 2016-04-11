package br.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.livraria.dominio.Livro;

public class LivroDaoImpl implements LivroDao {
	private static EntityManagerFactory emf;

	public LivroDaoImpl() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("livrarias_camargo");
		}
	}

	@Override
	public void adicionar(Livro l) throws SQLException {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		if (em.contains(l)) {
			em.persist(l);
		} else {
			em.merge(l);
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Livro> pesquisarPorTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Livro l) throws SQLException {
		EntityManager em = emf.createEntityManager();
		Livro l1 = em.getReference(Livro.class, l.getIsbn());
		em.getTransaction().begin();
		em.remove(l1);
		em.getTransaction().commit();
		em.close();
	}
}
