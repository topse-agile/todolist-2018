package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

class Repository {
	private Connection connection;
	private static TodoRepository repository;
	
	@BeforeAll
	static void setUpAll() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		repository = factory.openSession().getMapper(TodoRepository.class);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		Driver.load();
		connection = DriverManager.getConnection("jdbc:h2:mem:todo");
		Statement stmt = connection.createStatement();
		stmt.execute(
				"create table todo (title varchar, detail varchar, deadline date, created_date date)");
		stmt.close();
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
	}

	@Test
	void test() {
		TodoDao dao = new TodoDao();
		dao.setTitle("Foo");
		
		repository.insert(dao);
		
		List<TodoDao> result = repository.findAll();
		assertEquals(1, result.size());
	}

}
