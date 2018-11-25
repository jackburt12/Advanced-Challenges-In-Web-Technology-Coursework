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
        

        <c:forEach items="${leagues}" varStatus="i" var="league">
            bitch
            <p>
                ID : ${i.index + 1} Name : ${league.name} Max team : ${league.maxTeam}
            </p>
        </c:forEach>
        
    </body>
</html>
