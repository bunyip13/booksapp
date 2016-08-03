<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Books list</title>
	</head>
	<body>
		<a href="<c:url value="add" />"Add Book</a><br />
		<table border="1">
			<thead>
				<tr>
					<th>#</th>
					<th>Book title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td><a href="<c:url value="book-${status.index}" />"${book.title}</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>