<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head></head>
<body>
<h1>Registration</h1>
<form:form name='f' action="registration" method='POST' commandName="userForm">
    <table>
        <tr>
            <td>Login:</td>
            <td><form:input type='text' path='login'/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input type='password' path='password' /></td>
        </tr>
        <tr>
            <td><button name="submit" type="submit" value="Sign up" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>