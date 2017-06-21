package rip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/img")
public class RipRest {

	@Context
	private ServletContext context;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testGet() {
		return "hello";

	}

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(@MultipartForm FileUploadForm form) {
		String filename = Configuration.getDataPath(context) + "/tt.png";

		try {
			writeFile(form.getData(), filename);

			System.out.println("Done uploadFile()");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("OKKK").build();

	}

	@POST
	@Path("/savetext")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response saveText(String text) {

		String filename = Configuration.getDataPath(context) + "/tt.txt";

		try {
			writeFile(text.getBytes(), filename);

			System.out.println("Done saveText()");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("OKKK").build();

	}

	@GET
	@Path("/gettext")
	@Produces(MediaType.TEXT_PLAIN)
	public String getText() {
		String filename = Configuration.getDataPath(context) + "/tt.txt";
		String content = "";
		try {
			content = readFile(filename, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return "ERRO AO LER ARQUIVO";
		}
		System.out.println("Done getText()");
		return content;

	}

	// @POST
	// @Path("/status")
	// @Consumes(MediaType.TEXT_PLAIN)
	// @Produces(MediaType.TEXT_PLAIN)
	// public Response saveStatus(String text) {
	//
	// String filename = Configuration.getDataPath(context) + "/tt.txt";
	//
	// try {
	// writeFile(text.getBytes(), filename);
	//
	// System.out.println("Done saveText()");
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return Response.status(200).entity("OKKK").build();
	//
	// }

	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);
		System.out.println("writing file: " + file.getAbsolutePath());

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
