<%-- 
    Document   : test2
    Created on : Feb 5, 2023, 7:55:30 PM
    Author     : Chau Nhat Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <span>We already send a verification  code to your email.</span>

        <form action="verifyCodeController" method="POST">
            <input type="text" name="authcode" >
            <input type="submit" value="verify">
        </form>
    </body>
</html>
