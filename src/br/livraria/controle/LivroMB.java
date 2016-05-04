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
import javax.faces.event.ActionEvent;

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
	private UploadedFile[] files = new UploadedFile[2];

	private String[] imagens = new String[2];
	/*
	 * private List<String> imagens = new ArrayList<String>();
	 * 
	 * public List<String> getImagens() { return imagens; }
	 * 
	 * public void setImagens(List<String> imagens) { this.imagens = imagens; }
	 */

	private static int count = 0;

	public LivroMB() {
		LivroDao livroDao = new LivroDaoImpl();
		livroAtual = new Livro();
		livros = new ArrayList<Livro>();
		// imagens [0] = "resources/img/livro-jogosvorazes-4.jpg";
		// imagens [1] = "resources/img/guerra-civil-quadrinheiros.jpg";
		// System.out.println(imagens[0] + " outra " + imagens[1]);
		// imagens.add("resources/img/livro-jogosvorazes-4.jpg");
		// imagens.add("resources/img/guerra-civil-quadrinheiros.jpg");

		try {
			livros = livroDao.pesquisarPorTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String adiciona() {
		LivroDao livroDao = new LivroDaoImpl();
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
		String filePath = "C:\\Users\\Plutão\\Desktop\\foto\\";
		byte[] bytes = null;
		if (null != uploadedFile) {
			bytes = uploadedFile.getContents();
			BufferedOutputStream stream;
			System.err.println(filePath + fileNameUploaded);
			try {
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileNameUploaded)));
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

		imagens[0] = (filePath + fileNameUploaded);
		imagens[1] = (filePath + fileNameUploaded);

		String infoAboutFile = "<br/> Arquivo recebido: <b>" + fileNameUploaded + "</b><br/>"
				+ "Tamanho do Arquivo: <b>" + fileSizeUploaded + "</b>";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));
	}

	public void uploaded() {
		for (UploadedFile uploadedFile : files) {
			System.out.println(uploadedFile.getContentType());
			if (uploadedFile != null) {
				if (!("image/png".equals(uploadedFile.getContentType()))) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de arquivo inválido",
							"O arquivo deve ser do tipo png, jpg ou jpeg");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage message = new FacesMessage("Succesful",
							files[0].getFileName() + "e" + files[1].getFileName() + " is uploaded.");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		System.out.println(file.getFileName());

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

	/*
	 * public String[] getImagens() { return imagens; }
	 * 
	 * public void setImagens(String[] imagens) { this.imagens = imagens; }
	 */

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		LivroMB.count = count;
	}

	public String[] getImagens() {
		return imagens;
	}

	public void setImagens(String[] imagens) {
		this.imagens = imagens;
	}

	public UploadedFile[] getFiles() {
		return files;
	}

	public void setFiles(UploadedFile[] files) {
		this.files = files;
	}
}
