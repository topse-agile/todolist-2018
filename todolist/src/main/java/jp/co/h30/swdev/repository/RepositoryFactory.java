package jp.co.h30.swdev.repository;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class RepositoryFactory {
	private static final RepositoryFactory INSTANCE = new RepositoryFactory();
	
	private final SqlSessionFactory factory;
	private final TodoRepository repository;
	
	private RepositoryFactory() {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
			repository = factory.openSession().getMapper(TodoRepository.class);
		} catch (IOException e) {
			throw new RuntimeException("DB接続に失敗しました.", e);
			
		}
	}
	
	public static RepositoryFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * {@link TodoRepository}のインスタンスを生成します.<br>
	 * インスタンスはSingletonです.
	 * 
	 * @return repositoryのインスタンス
	 */
	public TodoRepository generateRepository() {
		return repository;
	}
}
