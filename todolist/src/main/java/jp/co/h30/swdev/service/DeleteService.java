package jp.co.h30.swdev.service;

import jp.co.h30.swdev.repository.RepositoryFactory;
import jp.co.h30.swdev.repository.TodoRepository;

public class DeleteService {
private TodoRepository repository;
	
	public DeleteService() {
		this.repository = RepositoryFactory.getInstance().generateRepository();
	}
	
	DeleteService(TodoRepository repository) {
		this.repository = repository;
	}
	
	public void execute() {
		repository.deleteAll();
	}
}
