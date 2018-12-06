<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${league.name}</title>
    <!-- JQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="http://maps.google.com/maps/api/js?key=AIzaSyBFjl1kuYl8NPCiendKbjzNPT-F0RxuAHo&sensor=true"></script>

    <script type="text/javascript">
        var setMarkers;
        var map;
        var geocoder = new google.maps.Geocoder();
        var myOptions = {
            zoom: 6,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        $( document ).ready(function() {
            map = new google.maps.Map(document.getElementById("map_container"),myOptions);

            var place = "England";
            geocoder.geocode( { 'address': place }, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });
        });
        function setMarkers(area,name) {
            geocoder.geocode( { 'address': area }, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        map: map,
                        title:name,
                        position: results[0].geometry.location
                    });
                    google.maps.event.addListener(marker, 'click', function() {highlightFixture(name);});
                } else {
                    alert("Geocode was not successful for the following reason: " + status);
                }
            });
        };

        function highlightFixture(name) {
            $($("[id='"+ name + "']")).css("background-color","#eee");
        }
    </script>

    <script type="text/javascript">
        function showFixtures() {
            $("#fixButton").hide();
            $("#fixForm").show();
            $("#fixForm").slideDown();
        };
    </script>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/group05_cw/league/all">League Viewer</a>
</nav>
<div class="jumbotron text-center">
    <div class="container">
        <h1>${league.name}</h1>
    </div>
</div>

<div class="container">
    <h3>Teams</h3>
    <table class="table">
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
            <td> <a href = "${league.id}/team/${team.id}">${team.name}</a> </td>
            <td> ${team.matchPlayed}</td>
            <td> ${team.won}</td>
            <td> ${team.draw}</td>
            <td> ${team.loss}</td>
            <td> ${team.goalDifference}</td>
            <td> ${team.points}</td>
        </tr>
        </c:forEach>
    </table>
</div>

<div class="container">
    <h3>Fixtures</h3>
    <table class="table">
        <tr>
            <th>Home</th>
            <th>Score</th>
            <th>Away</th>
        </tr>
        <c:forEach items="${fixtures}" varStatus="i" var="fixture">
            <tr id="${fixture.home}">
                <td> ${fixture.home} </td>
                <td> ${fixture.homeScore} - ${fixture.awayScore}</td>
                <td> ${fixture.away} </td>
            </tr>
            <script type="text/javascript">
                setMarkers("${fixture.location}","${fixture.home}");
            </script>
        </c:forEach>
    </table>
</div>

<div class="container">
    <h3>Enter Match Details</h3>
    <form id="fixForm" action="${league.id}" method="post">
        <div class="form-group">
            <label for="homeTeam">Home Team:</label>
            <select id="homeTeam" name="home">
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="awayTeam">Away Team:</label>
            <select id = "awayTeam" name = "away" >
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="homeScore">Home Score:</label>
            <input type="text" name="homeScore" value="0" id = "homeScore">
        </div>
        <div class="form-group">
            <label for="awayTeam">Away Team:</label>
            <input type="text" name="awayScore" value="0" id = "awayScore">
        </div>
        <input type="submit" value="Submit" />
    </form>
</div>

<style type="text/css">
    div#map_container{
        width:600px;
        height:500px;
    }
</style>
<c:url var="logoutUrl" value="/j_spring_security_logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Logout"/>
</form>
<div class="container" id="map_container"></div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/group05_cw/league/all">League Viewer</a>
</nav>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>