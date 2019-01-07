package jp.co.h30.swdev.service;

import jp.co.h30.swdev.bean.CompleteBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.RepositoryFactory;
import jp.co.h30.swdev.repository.TodoRepository;

public class CompleteService {
	private TodoRepository repository;

	public CompleteService() {
		this.repository = RepositoryFactory.getInstance().generateRepository();
	}

	public void execute(CompleteBean bean) {
		TodoDao dao = new TodoDao();
		dao.setId(bean.getId());

		repository.delete(dao);
	}

}
