package jp.co.h30.swdev.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.h30.swdev.bean.ListBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.RepositoryFactory;
import jp.co.h30.swdev.repository.TodoRepository;

public class ListService {
	private TodoRepository repository;
	
	public ListService() {
		this.repository = RepositoryFactory.getInstance().generateRepository();
	}
	
	ListService(TodoRepository repository) {
		this.repository = repository;
	}
	
	public List<ListBean> execute() {
		List<TodoDao> todos = repository.findAll();
		
		List<ListBean> results = new ArrayList<ListBean>();
		for(TodoDao dao : todos) {
			ListBean bean = new ListBean();
			bean.setTitle(dao.getTitle());
			bean.setDetail(dao.getDetail());
			
			java.sql.Date deadLine = dao.getDeadline();
			if(deadLine != null) {
				bean.setDeadline(new Date(deadLine.getTime()));
			}
			
			bean.setCreatedDate(new Date(dao.getCreatedDate().getTime()));
			results.add(bean);
		}
		
		return results;
	}
}
