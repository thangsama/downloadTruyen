package Crawl.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet (urlPatterns= {"/fillUrl"})
public class FillUrlServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	public FillUrlServlet() {
		super();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException{
		String url = request.getParameter("url");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/fillUrl.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/infoBook.jsp");
	dispatcher.forward(request, response);
	}
	
	
}
