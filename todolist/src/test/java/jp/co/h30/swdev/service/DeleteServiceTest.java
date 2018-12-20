package jp.co.h30.swdev.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.co.h30.swdev.repository.TodoRepository;

public class DeleteServiceTest {
	@Mock
	private TodoRepository repository;
	
	@InjectMocks
	private DeleteService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void callRepositoryWithDeleteApiProperly() {
		service.execute();
		
		verify(repository).deleteAll();
	}
}
