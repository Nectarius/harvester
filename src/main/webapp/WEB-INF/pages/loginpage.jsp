
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Авторизация</title>
    <link href="../../resources/assets/bootstrap-3.0.2/css/bootstrap.min.css"  rel="stylesheet">

    <link href="../../resources/assets/bootstrap-3.0.2/css/bootstrap-theme.min.css"  rel="stylesheet">

    <script src="../..//resources/assets/bootstrap-3.0.2/js/bootstrap.min.js"></script>
</head>
<body>

<div id="login-error">${error}</div>

<nav class="navbar navbar-default ">
    <div class="navbar-header ">
        <a class="navbar-brand">Эвенты</a>
    </div>
</nav>

<form action="../../j_spring_security_check" method="post" >

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

</form>

</body>
</html>