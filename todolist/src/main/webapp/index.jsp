<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="jp.co.h30.swdev.bean.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
	<h1>TodoList</h2>
	<h3>一覧</h3>
	<div data-test-id="list">
		<c:forEach items="${todos}" var="todo">
			<div data-test-id="todo">
				<span data-test-id="title"><c:out value="${todo.title}"></c:out></span>
				<span data-test-id="detail"><c:out value="${todo.detail}"></c:out></span>
				<span data-test-id="deadline"><c:out value="${todo.deadline}"></c:out></span>
				<span data-test-id="created-date"><c:out value="${todo.createdDate}"></c:out></span>
			</div>
		</c:forEach>
	</div>
	<a data-test-id="btn-register" href="/todolist/register.jsp">登録ページ</a>
</body>
</html>
