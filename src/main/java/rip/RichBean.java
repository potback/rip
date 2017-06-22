package rip;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean
public class RichBean implements Serializable {

	private static final long serialVersionUID = -6239437588285327644L;

	private String name;
	FileManager fileManager = new FileManager();

	@PostConstruct
	public void postContruct() {
		name = "John" + Configuration
				.getDataPath(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/uploads"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		String filename = Configuration
				.getDataPath(FacesContext.getCurrentInstance().getExternalContext().getRealPath(""))
				+ Configuration.getPngSrcFilename();
		String content = "";
		try {
			content = fileManager.readFile(filename, StandardCharsets.UTF_8);
		} catch (Exception e) {
			// e.printStackTrace();
			return "ERRO AO LER ARQUIVO " + filename;
		}
		System.out.println("Done getText()");
		return content;

	}
}