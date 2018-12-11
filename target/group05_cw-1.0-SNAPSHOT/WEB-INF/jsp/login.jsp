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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="league/all">League Viewer</a>
    </nav>
    <div class="jumbotron text-center">
        <div class="container">
            <h1>Login</h1>
        </div>
    </div>

    <div class="container">

        <form action="j_spring_security_check" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="j_username" class="sr-only">Login</label>
            <input id="j_username" name="j_username" size="20" maxlength="50" type="text" class="form-control" placeholder="Username" required autofocus/>
            <br/>
            <label for="j_password" class="sr-only">Password</label>
            <input id="j_password" name="j_password" size="20" maxlength="50" type="password" class="form-control" placeholder="Password" required size="20" maxlength="50"/>
            <br/>
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login"/>
        </form>

    </div>

</body>
</html>
