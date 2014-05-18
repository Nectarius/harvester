<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Авторизация</title>
    <link href="library/bootstrap-3.0.0/css/bootstrap.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="library/bootstrap-3.0.0/css/bootstrap-theme.css">
    <script src="library/bootstrap-3.0.0/js/bootstrap.js"></script>
</head>
<body onload="document.loginForm.j_username.focus();">

	<table width="100%" height="100%">
		<tr height="66%" style="vertical-align: middle;">
			<td align="center">Авторизация:<br /> <br />
				<form name="loginForm" id="loginForm"
					action="j_spring_security_check" method="post">
					<table>
						<tr>
							<td>Логин&nbsp;</td>
							<td><input type="text" name="j_username" /></td>
						</tr>
						<tr>
							<td>Пароль&nbsp;</td>
							<td><input type="password" name="j_password" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><button type="submit"  class="btn btn-success"
								> <i class="icon-user icon-white"></i> Sign in</button></td>
						</tr>
					</table>
				</form> &nbsp;<br /> &nbsp;<br /> &nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>


</body>
</html>
