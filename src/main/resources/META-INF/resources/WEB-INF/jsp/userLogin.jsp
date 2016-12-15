<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>
   <h1>Authorization</h1>
      <c:if test="${param.error != null}">
          <div>
              Authentication error
              <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
              </c:if>
          </div>
      </c:if>
      <c:if test="${param.logout != null}">
          <div>
              You successfully log out.
          </div>
      </c:if>
   <form name='f' action="login" method='POST'>
      <table>
         <tr>
            <td>Login:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="Sign in" /></td>
         </tr>
      </table>
  </form>
   <a href="/registration">Sign up</a>
</body>
</html>