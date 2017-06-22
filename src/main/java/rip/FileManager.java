package rip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

	public void writeFile(byte[] content, String filename) throws IOException {

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

	public String readFile(String path, Charset encoding) throws IOException {
		System.out.println("reading file: " + path);
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
