<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Football League Viewer</title>
    <style>
        #leagueForm {
            display:none;
        }
    </style>

    <script type="text/javascript">
        function showLeagueForm() {
            $("#leagueButton").hide();
            $("#leagueForm").slideDown('slow');
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/league/all">League Viewer</a>
    <sec:authorize access="isAuthenticated()">
        <c:url var="logoutUrl" value="/j_spring_security_logout"/>
        <form action="${logoutUrl}" method="post">
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Logout"/>
        </form>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <form action="/login">
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login"/>
        </form>
    </sec:authorize>

</nav>
<div class="jumbotron text-center">
    <div class="container">
        <h1>Leagues</h1>
    </div>
</div>
<div class="container">
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Teams</th>
        </tr>
            <c:forEach items="${leagues}" varStatus="i" var="league">
                <tr>
                    <td> <a href="${league.id}">${league.name}</a> </td>
                    <td> ${league.teamsList.size()} / ${league.maxTeams} </td>
                </tr>
            </c:forEach>
        </select>

    </table>
</div>

<div class="container">


    <sec:authorize access="hasRole('ADMIN')">
        <button class="btn btn-lg btn-primary btn-block" id="leagueButton" onclick="showLeagueForm()"> Add League </button>
    </sec:authorize>
    <div id="leagueForm">
        <h3>Add a League</h3>
        <form action="all" method="post">
            <div class="form-group">
                <label for="leagueName">League Name:  </label>
                <input type="text" name="leagueName" value="" placeholder="League Name" maxlength="25" id="leagueName">
            </div>
            <div class="form-group">
                <label for="leagueMaxTeams">Max Number of Teams:  </label>
                <input type="number" name="leagueMaxTeams" value="" placeholder="Maximum Teams" id="leagueMaxTeams" max="99">
            </div>
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit" />
        </form>
    </div>

</div>

</body>
</html>