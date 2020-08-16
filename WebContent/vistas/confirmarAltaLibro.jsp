<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gracias por tu visita</title>
<link rel="stylesheet" href="css/estilosLibros.css" type="text/css" />
</head>
<body>

	<div class="container">
		<section id="content">
			<form action="http://localhost:8080/LibreriaMVC/LibrosServlet?todo=Modificar">
				<h4>El libro se ha añadido correctamente en la base de datos</h4>
				<div>
					<input type="submit" value="Volver">
				</div>
				
			</form>
		</section>
	</div> 
</body>
</html>