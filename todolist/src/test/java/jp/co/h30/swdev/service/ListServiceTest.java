package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.co.h30.swdev.bean.ListBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

class ListServiceTest {
	@Mock
	private TodoRepository repository;
	
	@InjectMocks
	private ListService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	void canGetAllTodoItems() {
		List<TodoDao> selectResults = new ArrayList<TodoDao>();
		
		TodoDao dao = new TodoDao();
		dao.setTitle("hoge");
		dao.setCreatedDate(new java.sql.Date(new Date().getTime()));
		selectResults.add(dao);
		
		TodoDao anotherDao = new TodoDao();
		anotherDao.setCreatedDate(new java.sql.Date(new Date().getTime()));
		selectResults.add(anotherDao);
		
		when(repository.findAll()).thenReturn(selectResults);
		
		List<ListBean> results = service.execute();
		
		verify(repository).findAll();
		assertEquals(2, results.size());
	}

	@Test
    void canSetClosenessOfDeadline() {
		List<TodoDao> selectResults = new ArrayList<TodoDao>();

		TodoDao dao = new TodoDao();
		dao.setTitle("hoge");
		dao.setCreatedDate(new java.sql.Date(new Date().getTime()));
		dao.setDeadline(new java.sql.Date(new Date().getTime()));
		selectResults.add(dao);

		when(repository.findAll()).thenReturn(selectResults);
		List<ListBean> results = service.execute();

		ListBean targetListBean = results.get(0);
		String actual = targetListBean.getClosenessOfDeadline();

		assertEquals("closing", actual);
    }

}
