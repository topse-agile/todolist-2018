<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.h30.swdev.bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<a class="navbar-brand" href="#">Todo一覧</a>
		<ul class="navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" data-test-id="btn-register" href="/todolist/register.jsp">登録</a>
			</li>
		</ul>
	</nav>
	<table class="table table-hover" data-test-id="list">
		<thead>
			<tr>
				<th>タイトル</th>
				<th>説明</th>
				<th>期限</th>
				<th>作成日</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr data-test-id="todo">
					<td data-test-id="title"><c:out value="${todo.title}"></c:out></td>
					<td data-test-id="detail"><c:out value="${todo.detail}"></c:out></td>
					<td data-test-id="deadline"><fmt:formatDate value="${todo.deadline}" pattern="yyyy/MM/dd" /></td>
					<td data-test-id="created-date"><fmt:formatDate value="${todo.createdDate}" pattern="yyyy/MM/dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
