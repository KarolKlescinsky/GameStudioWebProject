<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${player == null}">
	<div id="login" align="center" class="content">

		<form method="post">
			<div class="login">
				<label for="playerName">Name:</label> <input id="playerName"
					class="playerName" type="text" name="playerName"
					placeholder="name ..."> <label for="playerPwd">Password:</label>
				<input id="playerPwd" class="playerPwd" type="password" name="playerPwd"
					placeholder="password ..."> <input type="hidden"
					name="action" value="login">
				<button type="submit">Login</button>
			</div>
		</form>
	</div>

</c:if>

<c:if test="${player == null}">
	<div align="center">
		<h3>Registration</h3>

		<form method="post">

			<input type="hidden" name="gameName" value="${gameName}">
			Name: <br><input id="name" id="playerName" type="text"
				name="playerName1"> <br> Password: <br><input id="pass1"
				type="password" name="password1"> <br> Confirm password: <br><input
				id="pass2" type="password" name="password2"
				onkeyup="checkPass(); return false;"> <input type="hidden"
				name="action" value="register">

			<p id="confirmMessage" class="confirmMessage"></p>
			<br> <input type="submit" value="Register">

		</form>
	</div>
	<script type="text/javascript">
		function checkPass() {
			var name = document.getElementById('name');
			var pass1 = document.getElementById('pass1');
			var pass2 = document.getElementById('pass2');

			var message = document.getElementById('confirmMessage');

			var green = "#66cc66";
			var red = "#ff6666";

			if (pass1.value == pass2.value) {

				pass2.style.border = "solid 2px green"
				message.style.color = green;
				message.innerHTML = "Passwords Match!"
			} else {
				pass2.style.border = "solid 2px red"
				message.style.color = red;
				message.innerHTML = "Passwords Do Not Match!"
			}
			
// 			if (name.value == ""){
// 				name.style.backgroundColor = red;
// 				message.style.color = red;
// 				message.innerHTML = "Name must be filled."
// 			}
		}
	</script>

</c:if>

<c:if test="${player != null}">
<div class="content">
    <div class="login">
        <p>Prihlásený ako <%=session.getAttribute("player")%></p>
    </div>
    <div class="logout">
    	<form method="post">
	    	<input type="hidden" name="action" value="logout">
	        <button type="submit">Logout</button>
        </form>
    </div>
</div>
</c:if>
