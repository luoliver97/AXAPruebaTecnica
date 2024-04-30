<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Personas</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Listado de personas</h1>
        <a href="insertPerson" class="btn btn-success mb-3">Agregar Persona</a>
        <table class="table table-striped table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Nombre</th>
                    <th>Edad</th>
                    <th>Teléfono</th>
                    <th>Dirección</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope['data']}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.age}</td>
                        <td>${item.phoneNumber}</td>
                        <td>${item.address}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <!-- Modal de Error -->
	<div class="modal" tabindex="-1" role="dialog" id="errorModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Error de Servicio</h5>
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