<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="http://maps.google.com/maps/api/js?key=AIzaSyBFjl1kuYl8NPCiendKbjzNPT-F0RxuAHo&sensor=true"></script>
    
    <script type="text/javascript">
        var setMarkers;
        var map;
        var geocoder = new google.maps.Geocoder();
        var myOptions = {
            zoom: 6,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        // loads the document to show the country the league is being taken place, the place is England for this manner
        $( document ).ready(function() {

            map = new google.maps.Map(document.getElementById("map_container"),myOptions);
            
            var place = "England";

            geocoder.geocode( { 'address': place }, function(results, status) {
                  if (status == google.maps.GeocoderStatus.OK) {
                          map.setCenter(results[0].geometry.location);
                  } else {
                          alert('Geocode was not successful for the following reason: ' + status);
                  }
            }); 
        });


          // shows a marker on the home turf where a fixture took place
          function setMarkers(area,name) {
            geocoder.geocode( { 'address': area }, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                   var marker = new google.maps.Marker({
                       map: map,
                       title:name,
                       position: results[0].geometry.location
                   });
                   google.maps.event.addListener(marker, 'click', function() {highlightFixture(name)});
                } else {
                  alert("Geocode was not successful for the following reason: " + status);
                }
            });
          };
          
          // highlights the fixtures that took place in the specific marker
          function highlightFixture(name) {
              $(".highlight").removeClass("highlight");
              $($("[id='"+ name + "']")).addClass("highlight");
              
          }
        // removes the add fixture button and shows the form for adding fixtures
        function showFixtures() {
            $("#fixButton").hide();
            $("#fixForm").slideDown();
        };
    </script>
    <style>
        .highlight {background-color : #eee;};
        div#map_container{
            width:600px;
            height:500px;
        }
    </style>
    </head>
    <body >
        <button type="button" onclick="showFixtures()" id="fixButton"> Add fixtures</button>
        
        <form hidden="true" id = "fixForm" action = "${league.id}" method="post">
            <br>
            <select id = "homeTeam" name = "home">
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.id}" onclick="alert(${team.id})">${team.name}</option>
                </c:forEach>
            </select>
            <br>
            <select id = "awayTeam" name = "away" >
                <option value = "Select Team"></option>
                <c:forEach items="${teams}" varStatus="i" var="team">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
            <br>
            Home Score:<br>
            <input type="text" name="homeScore" value="0" id = "homeScore">
            <br>
            Away Score:<br>
            <input type="text" name="awayScore" value="0" id = "awayScore">
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

        <table>
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
            
        <br>

        <div id="map_container"></div>        
 
    </body>
</html>
