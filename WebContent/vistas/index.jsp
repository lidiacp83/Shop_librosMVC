<%-- Página de Acceso a la aplicación --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.Login.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" href="css/estilosLogin.css" type="text/css" />
<title>Login</title>
</head>

<body>
	<div class="container">
		<section id="content">
			<form method="get" action="login">
				<h1>Acceso</h1>
					<input type="text" name="usuario" placeholder="Usuario" required id="username" />
					<input type="password" name="password" placeholder="Contraseña" required id="password" />
					<input type="submit" value="Entrar" /> <input type="reset" value="Borrar" />
			</form>
		</section>
	</div>

</body>
</html>