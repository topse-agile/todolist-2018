package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.co.h30.swdev.bean.CompleteBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

public class CompleteServiceTest {
	@Mock
	TodoRepository repository;

	@InjectMocks
	CompleteService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canDeleteTodoItem() {
		CompleteBean bean = new CompleteBean();
		bean.setId(UUID.randomUUID().toString());

		ArgumentCaptor<TodoDao> argument = ArgumentCaptor.forClass(TodoDao.class);
		service.execute(bean);

		verify(repository).delete(argument.capture());

		TodoDao actual = argument.getValue();
		assertEquals(bean.getId(), actual.getId());
	}
}
