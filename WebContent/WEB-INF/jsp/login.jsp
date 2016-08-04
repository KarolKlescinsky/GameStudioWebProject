<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%-- <c:if test="${userName}"> --%>

<!-- 	<form> -->
<!-- 		<div class="container"> -->

<!-- 			<input type="hidden" name="gameName" value="logMe"><br> -->


<!-- 			<label><b>Username</b></label> <input type="text" -->
<!-- 				placeholder="Enter Username" name="uname" required> <label><b>Password</b></label> -->
<!-- 			<input type="password" placeholder="Enter Password" name="psw" -->
<!-- 				required> -->

<!-- 			<button type="submit">Login</button> -->
<!-- 			<input type="checkbox" checked="checked"> Remember me -->
<!-- 		</div> -->

<!-- 		<div class="container" style="background-color: #f1f1f1"> -->
<!-- 			<button type="button" class="cancelbtn">Cancel</button> -->
<!-- 			<span class="psw">Forgot <a href="#">password?</a></span> -->
<!-- 		</div> -->
<!-- 	</form> -->

<%-- </c:if> --%>


<%-- <c:if test="${userName}"> --%>
	<div align="center <%=session.getAttribute("isUserRegistred")%>">
		<h3>Registration</h3>

		<form>

			<input type="hidden" name="gameName" value="${gameName}">
			Name: <input id="originalName" id="playerName" type="text"
				name="playerName"> <br> Password: <input id="pass1"
				type="text" name="password1"> <br> Confirm password: <input
				id="pass2" type="text" name="password2"
				onkeyup="checkPass(); return false;">
			<p id="confirmMessage" class="confirmMessage"></p>
			<br> <input type="submit" value="Send name">

		</form>
	</div>
	<script type="text/javascript">
		function checkPass() {

			var pass1 = document.getElementById('pass1');
			var pass2 = document.getElementById('pass2');
			//  var checkName = getParameterByName('${checkName}');

			var message = document.getElementById('confirmMessage');

			var goodColor = "#66cc66";
			var badColor = "#ff6666";

			if (pass1.value == pass2.value) {

				pass2.style.backgroundColor = goodColor;
				message.style.color = goodColor;
				message.innerHTML = "Passwords Match!"
			} else {
				pass2.style.backgroundColor = badColor;
				message.style.color = badColor;
				message.innerHTML = "Passwords Do Not Match!"
			}
		}
	</script>

<%-- </c:if> --%>
