package rip;

import javax.servlet.ServletContext;

public class Configuration {

	public static String getDataPath(ServletContext context) {
		// String warPath = context.getRealPath("/uploads");
		// String path =
		// StringUtils.isEmpty(System.getenv("OPENSHIFT_DATA_DIR")) ? warPath
		// : System.getenv("OPENSHIFT_DATA_DIR");
		return context.getRealPath("/uploads");
	}
}
