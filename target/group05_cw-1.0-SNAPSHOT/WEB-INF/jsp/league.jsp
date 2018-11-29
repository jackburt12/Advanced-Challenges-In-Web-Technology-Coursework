<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>fuck off spring</title>
    </head>
    <body>
        <h1>Click to create a league</h1>
        

        <table>
            <tr>
              <th>Name</th>
              <th>Teams</th> 
            </tr>
            <tr>
            <c:forEach items="${leagues}" varStatus="i" var="league">
                <td> <a href="${league.id}">${league.name}</a> </td>
                <td> ${league.teams} / 10 </td>
            </c:forEach>
            </tr>
        </table>
    </body>
</html>
