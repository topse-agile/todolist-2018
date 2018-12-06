<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
	<h2>TodoList</h2>
	<h3>登録</h3>
	<form action="register" method="post">
		<label for="title">タイトル</label>
		<input id="title" data-test-id="title" name="title" type="text" />
		
		<label for="detail">説明</label>
		<input id="detail" data-test-id="detail" name="detail" type="text" />
		
		<label for="deadline">期限</label>
		<input id="deadline" data-test-id="deadline" name="deadline" type="text" />
		
		<input data-test-id="btn-submit" type="submit" value="登録" />
		<input data-test-id="btn-cancel" type="submit" value="キャンセル" />
	</form>
</body>
</html>
