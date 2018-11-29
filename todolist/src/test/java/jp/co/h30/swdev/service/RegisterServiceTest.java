package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

public class RegisterServiceTest {
	private static final String DATE_FORMAT = "yyyyMMdd";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	private static final DateFormat FORMAT = new SimpleDateFormat(DATE_FORMAT);
	
	private static Connection connection;
	private static TodoRepository repository;

	private RegisterService service;

	@BeforeAll
	public static void setUpAll() throws SQLException, IOException {
		Driver.load();
		connection = DriverManager.getConnection("jdbc:h2:mem:todo");
		Statement stmt = connection.createStatement();
		stmt.execute(
				"create table todo (title varchar, detail varchar, deadline date, created_date date)");
		stmt.close();
		
//		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//		repository = factory.openSession().getMapper(TodoRepository.class);
	}
	
	@AfterAll
	public static void tearDownAll() throws SQLException {
		connection.close();
	}

	@BeforeEach
	public void setUp() throws Exception {
		service = new RegisterService();
		
		Statement stmt = connection.createStatement();
		stmt.execute("delete from todo");
		repository = service.repository;
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void successRegisterWithAllColumns() {
		RegisterBean bean = new RegisterBean();
		bean.setTitle("Foo");
		bean.setDetail("Bar");
		LocalDate date = LocalDate.now();
		bean.setDeadline(date.format(FORMATTER));

		boolean valid = service.execute(bean);
		assertTrue(valid);
		
		List<TodoDao> results = repository.findAll();
		assertEquals(1, results.size());
		
		TodoDao result = results.get(0);
		assertEquals("Foo", result.getTitle());
		assertEquals("Bar", result.getDetail());
		assertEquals(date.format(FORMATTER), FORMAT.format(result.getDeadline()));
		assertNotNull(result.getCratedDate());
	}
}
