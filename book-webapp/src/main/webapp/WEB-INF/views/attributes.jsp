<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Book attributes</title>
	</head>
	<body>
		<a href="list">Back to book list</a><br />
		<table border="1">
			<tbody>
				<tr>
					<th>Title</th>
					<td>${book.title}</td>
				</tr>
				<tr>
					<th>Date</th>
					<td><fmt:formatDate pattern="yyyy" value="${book.date}" /></td>
				</tr>
				<tr>
					<th>Price</th>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${book.price}" /></td>
				</tr>
				<tr>
					<th>Author</th>
					<td>${book.author}</td>
				</tr>
			</tbody>
		</table>
		<br />
		<hr />
	</body>
</html>