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
        <script src="http://maps.google.com/maps/api/js?key=AIzaSyBFjl1kuYl8NPCiendKbjzNPT-F0RxuAHo&sensor=true"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    
    
    <script type="text/javascript">
        
        // google map launched in this variable
        var map;
        
        // geocoder for coding within the map
        var geocoder = new google.maps.Geocoder();
        var myOptions = {
            zoom: 6,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        // launches the map on document ready and moves it to show England on the map
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

        // sets marker to the location variable in the team model
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
          
          // highlights what fixture took place in a certain marker
          function highlightFixture(name) {
              $(".highlight").removeClass("highlight")
              $($("[id='"+ name + "']")).addClass("highlight");
          }
          
          // shows home team form in 
        function showFixtures() {
            $("#fixButton").hide();
            $("#fixForm").slideDown();
        }
        
        // shows the rest of the form
        var optValue;
        var opt;
        function showFixtures2(name) {
            $("#awayTeam").append(opt);
            optValue = name.value;
            $(".form-group2").hide();
            $("#awayTeam").val('');
            $("#homeScore").val('0');
            $("#awayScore").val('0');
            $(".form-group2").slideDown();
            opt = $("#awayTeam option[value='" + optValue + "']");
            $("#awayTeam option[value='" + optValue + "']").remove();
        }
            
    </script>
    
    <style type="text/css">
        .highlight{
            background-color: #eee;
        }
        #map_container{
            width:400px;
            height:500px;
        }
        #fixForm {
            display:none;
        }
        .form-group2{
            display:none;
        }
        #fixButton {
            position: relative;
            bottom: 20px;
        }
        .fixContainer {
            position: relative;
            width:60%;
            overflow-y:auto;
            height: 500px;
        }
        .containerRight {
            width:35%;
            margin-left:5%;
        }
    </style>
    
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
            <div class="row">
            <div class="col-xs-8 fixContainer">
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
                <button class="btn btn-lg btn-primary btn-block" id="fixButton" onclick="showFixtures() "> Add results </button>

                <div id="fixForm">
                        <form action="${league.id}" method="post">
                            <div class="container">
                                <h3>Enter Match Details</h3>
                                <div class="form-group">
                                    <label for="homeTeam">Home Team:</label>
                                    <select id="homeTeam" name="home" onchange="showFixtures2(this)">
                                        <option value = "SelectTeam"></option>
                                        <c:forEach items="${teams}" varStatus="i" var="team">
                                            <option value="${team.id}" >${team.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group2">
                                    <label for="awayTeam">Away Team:</label>
                                    <select id = "awayTeam" name = "away" >
                                        <option value = "SelectTeam"></option>
                                        <c:forEach items="${teams}" varStatus="i" var="team">
                                            <option value="${team.id}">${team.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="homeScore">Home Score:</label>
                                    <input type="number" min="0" step="1" name="homeScore" value="0" id = "homeScore">
                                </div>
                                <div class="form-group">
                                    <label for="awayTeam">Away Team:</label>
                                    <input type="number" min="0" step="1" name="awayScore" value="0" id = "awayScore">
                                </div>
                                <input type="submit" value="Submit" />
                            </div>
                        </form>
                    </div>

                </div>
            </div>
            <div class="col-xs-4 containerRight">
                <div class="container" id="map_container"></div>
            </div>
        </div>
        </div>
        <br>
        <div class="container">
            <c:url var="logoutUrl" value="/j_spring_security_logout"/>
            <form action="${logoutUrl}" method="post">
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Logout"/>
            </form>
        </div>
        <br>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="/group05_cw/league/all">League Viewer</a>      
        </nav>
    </body>
</html>
