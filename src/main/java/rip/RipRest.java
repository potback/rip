package rip;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

	FileManager fileManager = new FileManager();

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

		try {
			String filename = Configuration.getDataPath(context.getRealPath("")) + Configuration.getPngSrcFilename();
			fileManager.writeFile(form.getData(), filename);

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
		String filename = Configuration.getDataPath(context.getRealPath("")) + Configuration.getTextFilename();
		try {
			fileManager.writeFile(text.getBytes(), filename);

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
		String filename = Configuration.getDataPath(context.getRealPath("")) + Configuration.getTextFilename();
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

	// @POST
	// @Path("/status")
	// @Consumes(MediaType.TEXT_PLAIN)
	// @Produces(MediaType.TEXT_PLAIN)
	// public Response saveStatus(String text) {
	//
	// String filename = Configuration.getDataPath() + "/tt.txt";
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

}
