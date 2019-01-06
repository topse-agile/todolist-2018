package jp.co.h30.swdev.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.message.Messages;
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
		List<String> messages = new ArrayList<>();
		bean.setMessages(messages);
		
		TodoDao dao = new TodoDao();
		dao.setId(UUID.randomUUID().toString());
		
		String title = bean.getTitle();
		if(title == null || title.isEmpty()) {
			messages.add(Messages.getMessage("err.title"));
		} else {
			dao.setTitle(bean.getTitle());
		}
		dao.setDetail(bean.getDetail());
		
		String deadlineStr = bean.getDeadline();
		if(deadlineStr != null && !deadlineStr.isEmpty()) {
			try {
				java.util.Date deadline = parseDate(deadlineStr);
				dao.setDeadline(new Date(deadline.getTime()));
			} catch (ParseException e) {
				messages.add(Messages.getMessage("err.deadline.format"));
			}
		}
		
		if(messages.size() >= 1) {
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
