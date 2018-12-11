<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <title>${team.name}</title>

    <style>
        #playerForm {
            display:none;
        }

    </style>

    <script type="text/javascript">
        $(document ).ready(function() {
            
            // disable player submit button
            $('#formPlayer :input[type="submit"]').prop('disabled', true);
            
            // check if the player input is valid for submission 
            function checkname() {
                if (!$('#name').val().trim()) {
                  $('#formPlayer :input[type="submit"]').prop('disabled', true);
                  //$('#teamLocation errorMSG').next().text("location must not have punctuation");
                  $('#name').next().text("name must not be empty");
                  console.log("invalid name");
                  return false;
                } else {
                   console.log("valid name");
                   $('#name').next().empty();
                   return true;
                }
            }
            
            // check if the player name input field is correct and turns on submission if it is
            $('#name').keyup(function() {
                if(checkname()){
                    console.log("we made it");
                    $('#formPlayer :input[type="submit"]').prop('disabled', false);
                    console.log("submit now");
                }
            });
        });
        
        
        function showPlayerForm() {
            $("#playerFormButton").hide();
            $("#playerForm").slideDown();
        }

    </script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="../../all">League Viewer</a>
    <c:url var="logoutUrl" value="/j_spring_security_logout"/>
    <form action="${logoutUrl}" method="post">
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Logout"/>
    </form>
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
            <th>Player Position</th>
        </tr>
        <c:forEach var="player" items="${team.players}">
            <tr>
                <td><c:out value="${player.number}"/></td>
                <td>${player.name}</td>
                <td>${player.position}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <sec:authorize access="hasRole('ADMIN')">
        <button class="btn btn-lg btn-primary btn-block" id="playerFormButton" onclick="showPlayerForm()"> Add Player </button>
    </sec:authorize>
    <div id="playerForm">
        <h3>Add a Player</h3>
        <form action="./${team.id}" method="post" id = "formPlayer">
            <div class="form-group">
                <label for="name">Player Name:  </label>
                <input type="text" name="name" value="" placeholder="Player Name" maxlength="25" id="name">
            </div>
            <div class="form-group">
                <label for="number">Player Number:  </label>
                <input type="number" name="number" value="" placeholder="Player Number" id="number" max="99">
            </div>
            <div class="form-group">
                <label for="position">Player Position:  </label>
                <select id="position" name="position">
                    <option value="GOALKEEPER">Goalkeeper</option>
                    <option value="DEFENDER">Defender</option>
                    <option value="MIDFIELDER">Midfielder</option>
                    <option value="FORWARD">Forward</option>

                </select>
            </div>
            <input type="hidden" name="leagueId" value="${league.id}">
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit" />
        </form>
    </div>
    <br>

    <p>Matches played: ${team.matchPlayed}</p>
    <p>Points: ${team.points}</p>
    <p>Location: ${team.location}</p>

    <sec:authorize access="hasRole('ADMIN')">
        <a class="btn btn-lg btn-primary btn-block" href="${team.id}/delete/"> Delete Team </a>
    </sec:authorize>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>