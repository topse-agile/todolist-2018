package jp.co.h30.swdev.repository;

import java.util.List;
import jp.co.h30.swdev.dao.TodoDao;

public interface TodoRepository {
	public int insert(TodoDao dao);
	
	public List<TodoDao> findAll();
}
