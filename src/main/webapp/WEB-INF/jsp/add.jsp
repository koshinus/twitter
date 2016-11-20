<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<h1>Twitter</h1>
		<a href='/'>Twitter</a><br/>
		<a href='/{id}/homepage'>Homepage</a><br/>
		<form name='postUserMessage' id='postUserMessage' method='post' action='#'>
            <h2>Enter your message</h2>
            <label for='message'>Message</label>
            <input type='text' id='message' name='message' size='30' value=''>
            <br/>
            <input type='submit' id='create' name='create' value='Post'/>
        </form>
	</body>
</html>