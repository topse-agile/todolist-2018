package jp.co.h30.swdev.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jp.co.h30.swdev.bean.RegisterBean;
import jp.co.h30.swdev.dao.TodoDao;
import jp.co.h30.swdev.repository.TodoRepository;

public class RegisterService {
	private static final String DATE_FORMAT = "yyyyMMdd";
	TodoRepository repository;
	
	public RegisterService() {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			repository = factory.openSession().getMapper(TodoRepository.class);
		} catch (IOException e) {
			throw new RuntimeException("DBアクセスに失敗ました.", e);
			
		}
	}
	
	/**
	 * @param bean 登録するTodoアイテムの情報
	 * @return Todoアイテムの登録に成功したら{@code true}, 失敗したら{@code false}を返却します.
	 */
	public boolean execute(RegisterBean bean) {
		TodoDao dao = new TodoDao();
		dao.setTitle(bean.getTitle());
		dao.setDetail(bean.getDetail());
		
		DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			java.util.Date deadline = format.parse(bean.getDeadline());
			dao.setDeadline(new Date(deadline.getTime()));
		} catch (ParseException e) {
			bean.setMessage("不正な日付フォーマットです");
			return false;
		}
		dao.setCratedDate(new Date(System.currentTimeMillis()));
		
		repository.insert(dao);
		
		return true;
	}
}
