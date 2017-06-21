package rip;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class Configuration {

	public static String getDataPath(ServletContext context) {
		String warPath = context.getRealPath("/uploads");
		String path = StringUtils.isEmpty(System.getenv("OPENSHIFT_DATA_DIR")) ? warPath
				: System.getenv("OPENSHIFT_DATA_DIR");
		return path;
	}
}
