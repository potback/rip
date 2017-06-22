package rip;

import org.apache.commons.lang3.StringUtils;

public class Configuration {

	public static String getDataPath(String realPath) {
		String warPath = realPath + "/uploads";
		String path = StringUtils.isEmpty(System.getenv("OPENSHIFT_DATA_DIR")) ? warPath
				: System.getenv("OPENSHIFT_DATA_DIR");

		return path + "/";
	}

	public static String getPngSrcFilename() {
		return "img_png_src.txt";
	}

	public static String getTextFilename() {
		return "tt.txt";
	}
}
