<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    
    <script type="text/javascript">
        function showFixtures() {
            $('body').prepend('jquery madness');
            $("#fixButton").hide();
            $("#fixForm").slideDown();
        };
        
    </script>
    </head>
    <body>
        <button type="button" onclick="showFixtures()" id="fixButton"> Add fixtures</button>
        <form hidden="true" id = "fixForm" action = "#" th:action="@{/add}" th:object="${fixture}" method="post">
            <br>
            <select id = "homeTeam" th:field="*{home}" >
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.name}">${team.name}</option>
                </c:forEach>
            </select>
            <br>
            <select id = "awayTeam" th:field="*{away}" >
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.name}">${team.name}</option>
                </c:forEach>
            </select>
            <br>
            Home Score:<br>
            <input type="text" name="homescore" value="0" id = "homeScore" th:field="*{homeScore}" >
            <br>
            Away Score:<br>
            <input type="text" name="awayscore" value="0" id = "awayScore" th:field="*{awayScore}" >
            <br><br>
            <input type="submit" value="Submit" />  
        </form>
        <table>
            <tr>
              <th>Name</th>
              <th>Match Played</th> 
              <th>Won</th>
              <th>Draw</th> 
              <th>Loss</th>
              <th>GD</th> 
              <th>Points</th>
            </tr>
            <tr>
            <c:forEach items="${teams}" varStatus="i" var="team">
                <td> <a href = "${leagueid}/team/${team.id}">${team.name}</a> </td>
                <td> ${team.matchPlayed}</td>
                <td> ${team.won}</td>
                <td> ${team.draw}</td>
                <td> ${team.loss}</td>
                <td> ${team.goalDifference}</td>
                <td> ${team.points}</td>
            </tr>
            </c:forEach>
            
        </table>
    </body>
</html>
