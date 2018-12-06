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
        <title>View Team</title>
    </head>
    <body>
        <h1>${team.name}</h1>
        <h2>Players:</h2>
        <c:forEach items="${team.players}" varStatus="i" var="player">
            <p>${player.number} : ${player.name}</p>
        </c:forEach>
        <p>Matches played: ${team.matchPlayed}</p>
        <p>Points: ${team.points}</p>
    </body>
</html>
