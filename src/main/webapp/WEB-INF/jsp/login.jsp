<%--
Created by IntelliJ IDEA.
User: Jack
Date: 11/29/2018
Time: 1:28 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true"%>

<!DOCTYPE html>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Please Log In to Your Account</h1>
    <p>Enter the following:</p>
    <form action="j_spring_security_check" method="post">
        <label for="j_username">Login</label>:
        <input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
        <br/>
        <label for="j_password">Password</label>:
        <input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
        <br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
