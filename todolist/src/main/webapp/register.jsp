<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-grid.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-reboot.min.css" />

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="#">Todo登録</a>
	</nav>
	<c:forEach items="${messages}" var="message">
		<div class="alert alert-danger" data-test-id="message" role="alert">
			<c:out value="${message}" />
		</div>
	</c:forEach>
	<form action="register" method="post">
		<div class="form-group">
			<label for="title">タイトル</label> <input id="title"
				class="form-control" data-test-id="title" name="title" type="text" />
		</div>
		<div class="form-group">
			<label for="detail">説明</label>
			<input id="detail" class="form-control"
				data-test-id="detail" name="detail" type="text" />
		</div>
		<div class="form-group">
		<label for="deadline">期限</label>
		<input id="deadline" class="form-control"
			data-test-id="deadline" name="deadline" type="text" />
		</div>
			<input class="btn btn-primary" data-test-id="btn-submit" type="submit" value="登録" />
			<input class="btn btn-secondary" data-test-id="btn-cancel" type="submit" value="キャンセル" />
	</form>
</body>
</html>
