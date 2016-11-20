<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<h1>Twitter</h1>
		<a href='/'>Twitter</a><br/>
		<a href='/${id}/add'>Add new message</a><br/>
            <c:forEach items="${messages}" var="msg">
                ${msg}
            </c:forEach>
        </form>
	</body>
</html>