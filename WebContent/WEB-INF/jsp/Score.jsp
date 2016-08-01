<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<table id="printTable">
	<tr>
		<th>Player's Name
		<th>Player's Score
	</tr>
	<c:forEach items="${Scores}" var="Scores">
		<tr>
			<td>${Scores.player.playerName}
			<td>${Scores.score}
		</tr>
	</c:forEach>
</table>

