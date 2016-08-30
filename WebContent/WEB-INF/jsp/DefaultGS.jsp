<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="DefaultGS.css">
<title>GameStudio</title>
</head>
<body>

	<h1>GameStudio</h1>
	<ul>
		<li><a class="menu" href="?gameName=Stones">Stones</a></li>
		<li><a class="menu" href="?gameName=Minesweeper">Minesweeper</a></li>
		<li><a class="menu" href="?gameName=GuessTheNumber">GuessTheNumber</a></li>
		<!-- 		<li><a href="?gameName=hangman">HangMan</a></li> -->
		<li><a class="menu" href="?gameName=Login">Login and Registration</a></li>
	</ul>
	<br>
	<table id="statsTable" <%=request.getAttribute("hideLoginTable")%>>

		<tr>
			<th></th>
			<th>Average Rating</th>
			<th>Count of Ratings</th>
			<th>Count of Comments</th>
			<th>Count of Players</th>
			<th>The Highest Score</th>
		</tr>
		<tr>
			<td>Stones</td>
			<td>${AvgStones}</td>
			<td>${CountStones}</td>
			<td>${CountCommentStones}</td>
			<td>${CountPlayerStones}</td>
			<td>${MaxStones}</td>
		</tr>
		<tr>
			<td>Minesweeper</td>
			<td>${AvgMinesweeper}</td>
			<td>${CountMinesweeper}</td>
			<td>${CountCommentMinesweeper}</td>
			<td>${CountPlayerMinesweeper}</td>
			<td>${MaxMinesweeper}</td>
		</tr>
		<tr>
			<td>GuessTheNumber</td>
			<td>${AvgGuessTheNumber}</td>
			<td>${CountGuessTheNumber}</td>
			<td>${CountCommentGuessTheNumber}</td>
			<td>${CountPlayerGuessTheNumber}</td>
			<td>${MaxGuessTheNumber}</td>
		</tr>
	</table>

	<div <%=request.getAttribute("hideRegistration")%>><jsp:include
			page="login.jsp" /></div>




	<br>
	<p class="numberText" <%=request.getAttribute("hideTables")%>>Number
		of total players: ${countofplayers}</p>
	<p class="numberText" <%=request.getAttribute("hideTables")%>>Number
		of total games: ${countofgames}</p>

	<div><jsp:include page="/${gameName}" /></div>

	<div class="out">
		<div class="leftPart" <%=request.getAttribute("hideTables")%>><jsp:include
				page="Comment.jsp" /></div>
		<div class="rightPart" <%=request.getAttribute("hideTables")%>><jsp:include
				page="Score.jsp" /></div>
	</div>

</body>
</html>