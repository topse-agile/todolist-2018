package jp.co.h30.swdev.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.h30.swdev.service.AdjustService;

/**
 * Servlet implementation class AdjustServlet
 */
public class AdjustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdjustService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdjustServlet() {
        super();
        service = new AdjustService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 受け入れテスト用であることと, WebDriver(Selenium)がPostリクエストを作る機能を持っていなかったのでGETに集約した
		String inputDate = (String) request.getParameter("criteriaDate");
		if(inputDate == null || inputDate.isEmpty()) {
			service.execute();
		} else {
			service.execute(inputDate);
		}
		
		String criteriaDate = System.getProperty(AdjustService.PROPERTY_KEY);
		if(criteriaDate == null) {
			criteriaDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		}
		request.setAttribute("criteriaDate", criteriaDate);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adjust.jsp");
		dispatcher.forward(request, response);
	}
}
