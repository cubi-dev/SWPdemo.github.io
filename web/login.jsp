<%-- 
    Document   : login
    Created on : Jan 30, 2023, 1:19:59 PM
    Author     : Chau Nhat Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="loginController" method="POST">
            <c:set var = "errors" value="${requestScope.LOGIN_ERROR}"/>
            Email <input type="text" name="txtEmail" 
                             value="${param.txtEmail}" /><br/>
            <c:if test="${not empty errors.emailLengthError}">
                <font color="red">
                ${errors.emailLengthError}
                </font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Login" name="btAction" /><br/>
            <c:if test="${not empty errors.loginFail}">
                <font color="red">
                ${errors.loginFail}
                </font><br/>
            </c:if>
            <a href="forgotPasswordPage">Forgot password?</a><br/>
            New member? <a href="signUpPage">Sign up</a> here<br/>
        </form><br/>
    </body>
</html>
