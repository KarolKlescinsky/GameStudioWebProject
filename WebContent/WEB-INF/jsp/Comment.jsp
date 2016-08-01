<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<table id="printTable" >
	<tr>
		<th>Player's Name
		<th>Player's Comment
	</tr>
	<c:forEach items="${Comments}" var="Comments">
		<tr>
			<td>${Comments.player.playerName}
			<td>${Comments.comment}
		</tr>
	</c:forEach>
</table>

