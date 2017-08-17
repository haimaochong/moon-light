package tomcat;

import org.apache.catalina.startup.Embedded;


public class StartMainTomcat {

	/** The Constant PORT. */
	public static final int PORT = 80;

	/** The Constant CONTEXT. */
	public static final String CONTEXT = "/";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		System.setProperty("catalina.base", System.getProperty("user.dir") + "/server");
		Embedded server = TomcatUtils.buildNormalServer(PORT, CONTEXT, "", "utf-8");
		server.start();
		System.out.println("Hit Enter in console to stop server");
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
			System.exit(0);
		}
	}

}
