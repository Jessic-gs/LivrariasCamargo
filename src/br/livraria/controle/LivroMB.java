package br.livraria.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.livraria.dao.LivroDao;
import br.livraria.dao.LivroDaoImpl;
import br.livraria.dominio.Livro;

@ManagedBean
@SessionScoped
public class LivroMB {

	private Livro livroAtual;
	private List<Livro> livros;
	
	public LivroMB(){
		LivroDao livroDao = new LivroDaoImpl();
		livroAtual = new Livro();
		livros = new ArrayList<Livro>();
		try {
			livros = livroDao.pesquisarPorTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String adiciona(){
		LivroDao livroDao = new LivroDaoImpl();
		//System.out.println("Premeiro  "+livroAtual.getCategorias().get(0).getCodigo());
		//System.out.println("Segundo " +livroAtual.getCategorias().get(1).getCodigo());


		try {
			livroDao.adicionar( livroAtual );
			livros = (livroDao.pesquisarPorTodos());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		livroAtual = new Livro();
		return "";
	}
	
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public Livro getLivroAtual() {
		return livroAtual;
	}
	public void setLivroAtual(Livro livroAtual) {
		this.livroAtual = livroAtual;
	}
}
