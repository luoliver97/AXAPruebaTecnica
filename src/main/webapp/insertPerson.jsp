<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insertar Persona</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h1 class="mb-3">Insertar Nueva Persona</h1>
		<a href="/AXAPruebaTecnica" class="btn btn-primary mb-3">Volver</a>
		<form action="insertPerson" method="POST">
			<div class="form-group">
				<label for="name">Nombre:</label> <input type="text" id="name"
					name="name" class="form-control" required
					pattern="[A-Z][a-z]+(?: [A-Z][a-z]+)*" maxlength="50"
					title="El nombre debe estar en formato Camel Case. Ejemplo: Juan Martinez">
			</div>
			<div class="form-group">
				<label for="age">Edad:</label> <input type="number" id="age"
					name="age" class="form-control" required min="0" max="99">
			</div>
			<div class="form-group">
				<label for="phoneNumber">Número de Teléfono:</label> <input
					type="text" id="phoneNumber" name="phoneNumber"
					class="form-control" required pattern="[0-9]{10}">
			</div>
			<div class="form-group">
				<label for="address">Dirección:</label> <input type="text"
					id="address" name="address" class="form-control" required
					pattern="[\w\s]+ \d+ # \d+\w* - \d+\w*">
			</div>
			<button type="submit" class="btn btn-success">Guardar
				Persona</button>
		</form>
	</div>

	<!-- Modal de Error -->
	<div class="modal" tabindex="-1" role="dialog" id="errorModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Error de Validación</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p style="color: red;">${errorMessage}</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
	    $(document).ready(function() {
	        var errorMsg = "<%=request.getAttribute("errorMessage")%>";
	        if (errorMsg && errorMsg.trim().length > 0 && errorMsg != 'null') {
				$('#errorModal').modal('show');
			}
		});
	</script>
</body>
</html>
