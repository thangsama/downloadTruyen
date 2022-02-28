package Crawl.servlets;

import java.io.IOException;
import java.util.List;

import Crawl.beans.Book;
import Crawl.utils.SiteUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/infoBook")
public class InfoBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InfoBookServlet() {
		super();
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String url = request.getParameter("url");
		List<Book> list =null;
		try {
			list=SiteUtil.getInfo(url);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/infoBook.jsp");
		dispatcher.forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		this.doGet(request, response);
	}

}
