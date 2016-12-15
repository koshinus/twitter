<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head></head>
<body>
<h1>Регистрация</h1>
<form:form name='f' action="registration" method='POST' commandName="userForm">
    <table>
        <tr>
            <td>Логин:</td>
            <td><form:input type='text' path='login'/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><form:input type='password' path='password' /></td>
        </tr>
        <tr>
            <td><button name="submit" type="submit" value="Зарегистрироваться" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>