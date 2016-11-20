<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<h1>Twitter</h1>
		<a href='/'>Twitter</a><br/>
		<a href='/register'>Registration</a><br/>
		<form name='authorizeUser' id='authorizeUser' method='put' action='#'>
            <h2>Enter your login and password</h2>
            <label for='login'>Login</label>
            <input type='text' id='login' name='login' size='30' value=''>
            <br/>
            <label for='password'>Password</label>
            <input type='text' id='password' name='password' size='30' value=''>
            <br/>
            <input type='submit' id='create' name='create' value='Authorize'/>
        </form>
	</body>
</html>