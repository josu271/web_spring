<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Cliente - Techmerch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <div class="container">
        <h1><i class="bi bi-pencil"></i> Editar Cliente</h1>

        <form action="${pageContext.request.contextPath}/clientes/editar" method="post" class="form-container">
            <input type="hidden" name="dniCliente" value="${cliente.dniCliente}">

            <div class="form-group">
                <label for="dniCliente">DNI:</label>
                <input type="number" id="dniCliente" value="${cliente.dniCliente}" disabled class="form-control">
                <small class="form-text">El DNI no se puede modificar</small>
            </div>

            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${cliente.nombre}" required
                       class="form-control" placeholder="Ingrese el nombre">
            </div>

            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="${cliente.apellido}" required
                       class="form-control" placeholder="Ingrese el apellido">
            </div>

            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" value="${cliente.direccion}"
                       class="form-control" placeholder="Ingrese la dirección">
            </div>

            <div class="form-group">
                <label for="correo">Correo Electrónico:</label>
                <input type="email" id="correo" name="correo" value="${cliente.correo}"
                       class="form-control" placeholder="Ingrese el correo electrónico">
            </div>

            <div class="form-group">
                <label for="celular">Celular:</label>
                <input type="text" id="celular" name="celular" value="${cliente.celular}"
                       class="form-control" placeholder="Ingrese el número de celular">
            </div>

            <div class="form-group">
                <label for="status">Estado:</label>
                <select id="status" name="status" class="form-control" required>
                    <option value="ACTIVO" ${cliente.status == 'ACTIVO' ? 'selected' : ''}>ACTIVO</option>
                    <option value="INACTIVO" ${cliente.status == 'INACTIVO' ? 'selected' : ''}>INACTIVO</option>
                </select>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-check-circle"></i> Actualizar Cliente
                </button>

                <a href="${pageContext.request.contextPath}/clientes/list" class="btn btn-secondary">
                    <i class="bi bi-arrow-left"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</body>
</html>