package jp.co.h30.swdev.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.RepositoryFactory;
import jp.co.h30.swdev.repository.TodoRepository;

public class RegisterService {
	private static final String DATE_FORMAT = "yyyyMMdd";
	private static final String DATE_FORMAT_WIHT_SLASH = "yyyy/MM/dd";
	
	private TodoRepository repository;
	
	public RegisterService() {
		this.repository = RepositoryFactory.getInstance().generateRepository();
	}
	
	RegisterService(TodoRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * @param bean 登録するTodoアイテムの情報
	 * @return Todoアイテムの登録に成功したら{@code true}, 失敗したら{@code false}を返却します.
	 */
	public boolean execute(RegisterBean bean) {
		TodoDao dao = new TodoDao();
		dao.setTitle(bean.getTitle());
		dao.setDetail(bean.getDetail());
		
		try {
			java.util.Date deadline = parseDate(bean.getDeadline());
			dao.setDeadline(new Date(deadline.getTime()));
		} catch (ParseException e) {
			bean.setMessage("不正な日付フォーマットです");
			return false;
		}
		dao.setCreatedDate(new Date(System.currentTimeMillis()));
		
		repository.insert(dao);
		
		return true;
	}
	
	private java.util.Date parseDate(String dateStr) throws ParseException{
		String pattern;
		if(dateStr.contains("/")) {
			pattern = DATE_FORMAT_WIHT_SLASH;
		} else {
			pattern = DATE_FORMAT;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateStr);
	}
}
