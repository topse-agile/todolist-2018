package jp.co.h30.swdev.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.service.RegisterService;

/**
 * Servlet implementation class Register
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegisterBean bean = new RegisterBean();
		bean.setTitle(request.getParameter("title"));
		bean.setDeadline(request.getParameter("deadline"));
		bean.setDetail(request.getParameter("detail"));
		
		boolean result = new RegisterService().execute(bean);
		if(result) {
			response.sendRedirect("/todolist/");
		} else {
			request.setAttribute("messages", bean.getMessages());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		}
	}

}
