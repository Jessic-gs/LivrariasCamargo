package br.livraria.controle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.livraria.dao.LivroDao;
import br.livraria.dao.LivroDaoImpl;
import br.livraria.dominio.Livro;

@ManagedBean
@SessionScoped
public class LivroMB {

	private Livro livroAtual;
	private List<Livro> livros;
	private UploadedFile file;

	public LivroMB() {
		LivroDao livroDao = new LivroDaoImpl();
		livroAtual = new Livro();
		livros = new ArrayList<Livro>();
		try {
			livros = livroDao.pesquisarPorTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String adiciona() {
		LivroDao livroDao = new LivroDaoImpl();
		// System.out.println("Premeiro
		// "+livroAtual.getCategorias().get(0).getCodigo());
		// System.out.println("Segundo "
		// +livroAtual.getCategorias().get(1).getCodigo());

		try {
			livroDao.adicionar(livroAtual);
			livros = (livroDao.pesquisarPorTodos());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		livroAtual = new Livro();
		return "";
	}

	public void upload(FileUploadEvent fileUploadEvent) {
		UploadedFile uploadedFile = fileUploadEvent.getFile();
		String fileNameUploaded = uploadedFile.getFileName();
		long fileSizeUploaded = uploadedFile.getSize();

		System.out.println(fileNameUploaded);
		String filePath = "/LivrariasCamargo/WebContent/img/";
		byte[] bytes = null;
		if (null != uploadedFile) {
			bytes = uploadedFile.getContents();
			BufferedOutputStream stream;
			System.err.println(filePath + fileNameUploaded);
			try {
				stream = new BufferedOutputStream(new FileOutputStream(
						new File(filePath + fileNameUploaded)));
				try {
					stream.write(bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String infoAboutFile = "<br/> Arquivo recebido: <b>" + fileNameUploaded + "</b><br/>"
				+ "Tamanho do Arquivo: <b>" + fileSizeUploaded + "</b>";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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
