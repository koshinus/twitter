<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<h1>Twitter</h1>
		<a href='/'>Twitter</a><br/>
		<a href='/messages'>Messages</a><br/>
		<a href='/add'>Add new message</a><br/>
		<a href='/delete'>Delete message</a><br/>
		${errorMessage}
	</body>
</html>