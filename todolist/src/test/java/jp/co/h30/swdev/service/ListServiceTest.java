package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.h2.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.h30.swdev.bean.ListBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.RepositoryFactory;
import jp.co.h30.swdev.repository.TodoRepository;

class ListServiceTest {
	private static Connection connection;
	private static TodoRepository repository;

	private ListService service;
	
	@BeforeAll
	public static void setUpAll() throws SQLException, IOException {
		Driver.load();
		connection = DriverManager.getConnection("jdbc:h2:mem:todo");
		Statement stmt = connection.createStatement();
		stmt.execute(
				"create table todo (title varchar, detail varchar, deadline date, created_date date)");
		stmt.close();		
	}
	
	@AfterAll
	public static void tearDownAll() throws SQLException {
		connection.close();
	}

	@BeforeEach
	public void setUp() throws Exception {
		service = new ListService();
		
		Statement stmt = connection.createStatement();
		stmt.execute("delete from todo");
		repository = RepositoryFactory.getInstance().generateRepository();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	void canGetAllTodoItems() {
		TodoDao dao = new TodoDao();
		dao.setTitle("hoge");
		dao.setCreatedDate(new java.sql.Date(new Date().getTime()));
		repository.insert(dao);
		repository.insert(dao);
		List<ListBean> results = service.execute();
		
		assertEquals(2, results.size());
	}

}
