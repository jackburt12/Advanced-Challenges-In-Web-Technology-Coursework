<%-- 
    Document   : viewTeam
    Created on : 03-Dec-2018, 13:06:37
    Author     : matt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${team.name}</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/group05_cw/league/all">League Viewer</a>
</nav>
<div class="jumbotron text-center">
    <div class="container">
        <h1>${team.name}</h1>
    </div>
</div>
<div class="container">
    <h3>Players:</h3>
    <table class="table">
        <tr>
            <th>Player Number</th>
            <th>Player Name</th>
        </tr>
        <c:forEach var="p" begin="0" end="${team.players.size()-1}">
            <tr>
                <td>${team.players.get(p).number}</td>
                <td>${team.players.get(p).name}</td>
            </tr>
        </c:forEach>
    </table>
    <p>Matches played: ${team.matchPlayed}</p>
    <p>Points: ${team.points}</p>
</div>

<c:url var="logoutUrl" value="/j_spring_security_logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Logout"/>
        </form>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>