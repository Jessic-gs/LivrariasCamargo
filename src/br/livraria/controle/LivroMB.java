package br.livraria.controle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.livraria.dao.LivroDao;
import br.livraria.dao.LivroDaoImpl;
import br.livraria.dominio.Categoria;
import br.livraria.dominio.Livro;
import extras.UploadArquivo;

@ManagedBean
@SessionScoped
public class LivroMB {

	private Livro livroAtual;
	private UploadArquivo arquivo = new UploadArquivo();
	private UploadArquivo arquivo2 = new UploadArquivo();

	private List<Livro> livros;
	private List<Livro> livrosFiltrado;
	private UploadedFile[] files = new UploadedFile[2];

	public LivroMB() {
		LivroDao livroDao = new LivroDaoImpl();
		livroAtual = new Livro();
		livros = new ArrayList<Livro>();

		try {
			livros = livroDao.pesquisarPorTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		livrosFiltrado = livros;
	}

	public String salva() {
		LivroDao livroDao = new LivroDaoImpl();
		if (this.livroAtual.getId() == 0) {
			System.out.println("Isbn invalido");
		} else {
			FacesMessage msg = new FacesMessage("O Livro : ", this.livroAtual.getNome() + " foi inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(livroAtual.getIlustracao().get(1));
			this.arquivo.gravar();
			this.arquivo2.gravar();
			try {
				livroDao.adicionar(livroAtual);
				livros = (livroDao.pesquisarPorTodos());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			livroAtual = new Livro();

		}
		return "";
	}

	public void filtraNome() {
		ArrayList<Livro> ls = new ArrayList<Livro>();
		for (Livro livro : livros) {
			if (livro.getNome().contains(livroAtual.getNome())) {
				ls.add(livro);
			}
		}
		livrosFiltrado = ls;
	}

	public void filtrar() {
		String id = null;
		String idFiltro = null;
		ArrayList<Livro> ls = new ArrayList<Livro>();
		for (Livro livro : livros) {
			id = String.valueOf(livro.getId());
			idFiltro = String.valueOf(livroAtual.getId());
			if (id.contains(idFiltro)) {
				ls.add(livro);
			}
		}
		livroAtual.setId(0);
		livrosFiltrado = ls;
	}
	public void exibirTodos() {
		livrosFiltrado = livros;
	}
	public String cancelar() {
		livroAtual = new Livro();
		return "";
	}

	public void uploadAction() {
		ArrayList<String> ilustracoes = new ArrayList<String>();
		if (files[0].getFileName().equalsIgnoreCase("") || files[1].getFileName().equalsIgnoreCase("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carregue as Duas Imagens", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			arquivo2.fileUpload(files[1], ".jpg", "/image/");
			ilustracoes.add(arquivo2.getNome());
			arquivo.fileUpload(files[0], ".jpg", "/image/");
			ilustracoes.add(arquivo.getNome());
			livroAtual.setIlustracao(ilustracoes);

			salva();
		}
	}

	public void visualizarLivro(Livro l) {
		livroAtual = l;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("visualizar_livro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(Livro l) {
		livroAtual = l;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adiciona_livro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UploadArquivo getArquivo2() {
		return arquivo2;
	}

	public void setArquivo2(UploadArquivo arquivo2) {
		this.arquivo2 = arquivo2;
	}

	public UploadArquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadArquivo arquivo) {
		this.arquivo = arquivo;
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

	public UploadedFile[] getFiles() {
		return files;
	}

	public void setFiles(UploadedFile[] files) {
		this.files = files;
	}

	public List<Livro> getLivrosFiltrado() {
		return livrosFiltrado;
	}

	public void setLivrosFiltrado(List<Livro> livrosFiltrado) {
		this.livrosFiltrado = livrosFiltrado;
	}
}
