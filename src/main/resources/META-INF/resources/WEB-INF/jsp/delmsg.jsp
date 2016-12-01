<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<h1>Twitter</h1>
		<a href='/'>Twitter</a><br/>
		<a href='/msgs'>Messages</a><br/>
		<a href='/addmsg'>Add new message</a><br/>
		<form name='deleteMessage' id='deleteMessage' method='delete' action='#'>
            <h2>Enter id of delete message</h2>
            <label for='message'>Message</label>
            <input type='text' id='id' name='id' size='10' value=''>
            <br/>
            <input type='submit' id='delete' name='delete' value='Post'/>
        </form>
	</body>
</html>