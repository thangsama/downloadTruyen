package Crawl.utils;



import org.jsoup.Connection;

import jakarta.servlet.ServletRequest;

public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

	// Save connection to attribute of request
	/// Those info just stand there during the request time
	//// Until the site give data to user
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);

	}

	// Take the Connection which saved in Attribute of request
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}





	
}
