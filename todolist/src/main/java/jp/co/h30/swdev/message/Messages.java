package jp.co.h30.swdev.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Messages {
	private static final Properties PROPERTIES = new Properties();
	static {
		InputStream inputStream = Messages.class.getClassLoader().getResourceAsStream("jp/co/h30/swdev/message/messages.properties");
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			PROPERTIES.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(255);
		}
	}
	
	/**
	 * 指定されたキーに対応するメッセージを返却します。<br>
	 * 存在しないキーを指定した場合は空文字列を返却します。
	 * 
	 * @param key メッセージのキー
	 * @return キーに対応するメッセージ または 空文字列
	 */
	public static String getMessage(String key) {
		return PROPERTIES.getProperty(key, "");
	}
}
