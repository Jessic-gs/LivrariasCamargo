package extras;

import java.io.File;
import java.io.FileOutputStream;
import org.primefaces.model.UploadedFile;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;

public class UploadArquivo {
	private String diretorio;
	private String caminho;
	private byte[] arquivo;
	private String nome;

	public UploadArquivo() {
	}

	public String getNome() {
		return nome;
	}

	public String getRealPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

		return context.getRealPath("/");
	}

	public void fileUpload(UploadedFile event, String type, String diretorio) {
		try {
			this.nome = new java.util.Date().getTime() + type;
			this.caminho = getRealPath() + diretorio + getNome();
			this.arquivo = event.getContents();

			File file = new File(getRealPath() + diretorio);
			System.out.println(file);
			file.mkdirs();

		} catch (Exception ex) {
			System.out.println("Erro no upload do arquivo" + ex);
		}
	}

	public void gravar() {

		try {
			System.out.println(this.caminho);
			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho);
			fos.write(this.arquivo);
			fos.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}