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
import br.livraria.dominio.Livro;
import extras.UploadArquivo;

@ManagedBean
@SessionScoped
public class LivroMB {

	private Livro livroAtual;
	private UploadArquivo arquivo = new UploadArquivo();
	private UploadArquivo arquivo2 = new UploadArquivo();

	private List<Livro> livros;
	private UploadedFile[] files = new UploadedFile[2];

	private List<String> imagens = new ArrayList<String>();

	public LivroMB() {
		LivroDao livroDao = new LivroDaoImpl();
		livroAtual = new Livro();
		livros = new ArrayList<Livro>();

		// imagens[1] = "C:\\Users\\Plutão\\Pictures\\1° Premio\\91.jpg";
		// System.out.println(imagens[0] + " outra " + imagens[1]);
		imagens.add("1462376935311.jpg");
		imagens.add("1462376935312.jpg");

		try {
			livros = livroDao.pesquisarPorTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String adiciona() {
		LivroDao livroDao = new LivroDaoImpl();
		if (this.livroAtual.getId() == 0) {
			System.out.println("Isbn invalido");
		} else {
			FacesMessage msg = new FacesMessage("O Livro : ", this.livroAtual.getNome() + " foi inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public void handleKeyEvent() {
		livroAtual.setNome(livroAtual.getNome().toUpperCase());
	}

	public void uploadAction() {
		ArrayList<String> ilustracoes = new ArrayList<String>();
		if (files[0].getFileName().equalsIgnoreCase("") || files[1].getFileName().equalsIgnoreCase("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carregue as Duas Imagens", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage("Succesful", files[0].getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			arquivo2.fileUpload(files[1], ".jpg", "/image/");
			ilustracoes.add(arquivo2.getNome());
			arquivo.fileUpload(files[0], ".jpg", "/image/");
			ilustracoes.add(arquivo.getNome());

			livroAtual.setIlustracao(ilustracoes);

			adiciona();
		}
	}

	public UploadArquivo getArquivo2() {
		return arquivo2;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
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
}
