package jp.co.h30.swdev.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.Driver;

import jp.co.h30.swdev.bean.ListBean;
import jp.co.h30.swdev.service.ListService;

/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() throws SQLException {
        super();
        Driver.load();
		Connection connection = DriverManager.getConnection("jdbc:h2:mem:todo;DB_CLOSE_DELAY=-1");
		Statement stmt = connection.createStatement();
		stmt.execute(
				"create table todo (title varchar, detail varchar, deadline date, created_date date)");
		stmt.close();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ListBean> todos = new ArrayList<ListBean>();
		todos = new ListService().execute();
		request.setAttribute("todos", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
