package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

public class RegisterServiceTest {
	private static final String DATE_FORMAT = "yyyyMMdd";
	private static final String DATE_FORMAT_WITH_SLASH = "yyyy/MM/dd";
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	private static final DateFormat FORMAT = new SimpleDateFormat(DATE_FORMAT);

	@Mock
	private TodoRepository repository;

	@InjectMocks
	private RegisterService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void canRegisterWithAllColumns() {
		RegisterBean bean = new RegisterBean();
		bean.setTitle("Foo");
		bean.setDetail("Bar");
		LocalDate date = LocalDate.now();
		bean.setDeadline(date.format(FORMATTER));

		ArgumentCaptor<TodoDao> argument = ArgumentCaptor.forClass(TodoDao.class);

		boolean valid = service.execute(bean);

		assertTrue(valid);
		verify(repository).insert(argument.capture());

		TodoDao actualArgument = argument.getValue();
		assertEquals(bean.getTitle(), actualArgument.getTitle());
		assertEquals(bean.getDetail(), actualArgument.getDetail());
		assertEquals(date.format(FORMATTER), FORMAT.format(actualArgument.getDeadline()));
		assertNotNull(actualArgument.getCreatedDate());
	}

	@Test
	public void canRegisterWithSlashSeparatedDeadline() {
		RegisterBean bean = new RegisterBean();
		bean.setTitle("Foo");
		bean.setDetail("Bar");
		LocalDate date = LocalDate.now();
		bean.setDeadline(date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_SLASH)));

		ArgumentCaptor<TodoDao> argument = ArgumentCaptor.forClass(TodoDao.class);
		boolean valid = service.execute(bean);

		assertTrue(valid);
		verify(repository).insert(argument.capture());
		
		TodoDao actualArgument = argument.getValue();
		assertEquals(bean.getTitle(), actualArgument.getTitle());
		assertEquals(bean.getDetail(), actualArgument.getDetail());
		assertEquals(date.format(FORMATTER), FORMAT.format(actualArgument.getDeadline()));
		assertNotNull(actualArgument.getCreatedDate());
	}
	
	@Test
	public void canRegisterWithoutDetailAndDeadline() {
		RegisterBean bean = new RegisterBean();
		bean.setTitle("Hoge");
		
		ArgumentCaptor<TodoDao> argument = ArgumentCaptor.forClass(TodoDao.class);
		boolean valid = service.execute(bean);
		
		assertTrue(valid);
		verify(repository).insert(argument.capture());
		
		TodoDao actualArgument = argument.getValue();
		assertEquals(bean.getTitle(), actualArgument.getTitle());
		assertNull(actualArgument.getDetail());
		assertNull(actualArgument.getDeadline());
		assertNotNull(actualArgument.getCreatedDate());
	}
}
