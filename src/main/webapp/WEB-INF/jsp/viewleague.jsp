<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>${league.name}</title>
    <script src="http://maps.google.com/maps/api/js?key=AIzaSyBFjl1kuYl8NPCiendKbjzNPT-F0RxuAHo&sensor=true"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <style type="text/css">
        .highlight{
            background-color: #eee;
        }
        #map_container{
            width:400px;
            height:500px;
        }
        #teamForm {
            display: none;
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
            // displays england on the map
            geocoder.geocode( { 'address': place }, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });
            
            // disables submit button unless fields are fulfilled
            $('#teamForm :input[type="submit"]').prop('disabled', true);
            
            // checks if location field is valid either no punctuation or no 
            function checklocation() {
                if(!$('#teamLocation').val().trim()) {
                    return false;
                }
                if (/[.,\/#!$%\^&\*;:{}=\-_`~()'']/g.test($('#teamLocation').val())) {
                  $('#teamForm :input[type="submit"]').prop('disabled', true);
                  //$('#teamLocation errorMSG').next().text("location must not have punctuation");
                  $('#teamLocation').next().text("location must not have punctuation");
                  return false;
                } else {
                geocoder.geocode( { 'address': $('#teamLocation').val() }, function(results, status) {
                    if (status != google.maps.GeocoderStatus.OK) {
                        $('#teamForm :input[type="submit"]').prop('disabled', true);
                        $('#teamLocation').next().text("location does not exist");
                        return false;
                    }
                });
                $('#teamLocation').next().empty();
                return true;
                }
            }
            
            // check if name isn't empty
            function checkteamname() {
                if (!$('#teamName').val().trim()) {
                  $('#teamForm :input[type="submit"]').prop('disabled', true);
                  //$('#teamLocation errorMSG').next().text("location must not have punctuation");
                  $('#teamName').next().text("name must not be empty");
                  return false;
                } else {
                   $('#teamName').next().empty();
                   return true;
                }
            }
            
            // check if both fields are valid for the form to be able to submit
            $('#teamLocation').keyup(function() {
                if(checkteamname()){
                    if(checklocation()) {
                        $('#teamForm :input[type="submit"]').prop('disabled', false);
                    }
                }
            });
            // check if both fields are valid for the form to be able to submit
            $('#teamName').keyup(function() {
                if(checkteamname()){
                    if(checklocation()) {
                        $('#teamForm :input[type="submit"]').prop('disabled', false);
                    }
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

        function showTeamForm() {
            $("#teamButton").hide();
            $("#teamForm").slideDown();
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

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="all">League Viewer</a>
    <c:url var="logoutUrl" value="/j_spring_security_logout"/>
    <form action="${logoutUrl}" method="post">
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Logout"/>
    </form>
</nav>
<div class="jumbotron text-center">
    <div class="container">
        <h1>${league.name}</h1>
    </div>
</div>

<div class="container">
    <h3>League Table</h3>
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

    <sec:authorize access="hasRole('ADMIN')">
        <button class="btn btn-lg btn-primary btn-block" id="teamButton" onclick="showTeamForm()"> Add Team </button>
    </sec:authorize>
    <div id="teamForm">
        <h3>Add a Team</h3>
        <form action="${league.id}/team/add_team" method="post">
            <div class="form-group">
                <label for="teamName">Team Name:  </label>
                <input type="text" name="teamName" value="" placeholder="Team Name" maxlength="25" id="teamName"> <div> </div>
            </div>
            <div class="form-group">
                <label for="teamLocation">Location of Team:  </label>
                <input type="text" name="teamLocation" value="" placeholder="Team Location" id="teamLocation" max="99"> <div id="errorMSG"></div>
            </div>
            <input type="hidden" name="leagueId" value="${league.id}">
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit"/>
        </form>
    </div>

</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-xs-8 fixContainer">
            <div class="container">
                <h3>Results</h3>
                <table class="table">
                    <tr>
                        <th>Home</th>
                        <th>Score</th>
                        <th>Away</th>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th>Delete</th>
                        </sec:authorize>
                    </tr>
                    <c:forEach items="${league.fixturesList}" varStatus="i" var="fixture">
                        <tr id="${fixture.home.name}">
                            <td> ${fixture.home.name} </td>
                            <td> ${fixture.homeScore} - ${fixture.awayScore}</td>
                            <td> ${fixture.away.name} </td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td>
                                    <form action="${league.id}/delete_fixture" method="get">
                                        <input type="hidden" name="fixtureId" value="${fixture.id}"/>
                                        <button class="fa fa-trash" aria-hidden="true" type="submit"/>
                                    </form>
                                </td>
                            </sec:authorize>
                        </tr>
                        <script type="text/javascript">
                            setMarkers("${fixture.home.location}","${fixture.home.name}");
                        </script>
                    </c:forEach>
                </table>
                <sec:authorize access="hasRole('ADMIN')">
                    <button class="btn btn-lg btn-primary btn-block" id="fixButton" onclick="showFixtures()"> Add Result </button>
                </sec:authorize>
                <div id="fixForm">
                    <form action="${league.id}" method="post" id ="formFix" >
                        <div class="container">
                            <h3>Enter Match Details</h3>
                            <div class="form-group">
                                <label for="homeTeam">Home Team:</label>
                                <select id="homeTeam" name="home" onchange="showFixtures2(this)">
                                    <option value = ""></option>
                                    <c:forEach items="${teams}" varStatus="i" var="team">
                                        <option value="${team.id}" >${team.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group2">
                                <label for="awayTeam">Away Team:</label>
                                <select id = "awayTeam" name = "away" >
                                    <option value = ""></option>
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
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/group05_cw/league/all">League Viewer</a>
</nav>
</body>
</html>
