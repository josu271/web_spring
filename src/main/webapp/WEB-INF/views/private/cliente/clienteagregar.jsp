<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <div class="container">
        <h1><i class="bi bi-person-plus"></i> Crear Nuevo Cliente</h1>

        <form action="${pageContext.request.contextPath}/clientes/crear" method="post" class="form-container">
            <div class="form-group">
                <label for="dniCliente">DNI:</label>
                <input type="number" id="dniCliente" name="dniCliente" required class="form-control">
            </div>

            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required class="form-control">
            </div>

            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" required class="form-control">
            </div>

            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" class="form-control">
            </div>

            <div class="form-group">
                <label for="correo">Correo:</label>
                <input type="email" id="correo" name="correo" class="form-control">
            </div>

            <div class="form-group">
                <label for="celular">Celular:</label>
                <input type="text" id="celular" name="celular" class="form-control">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-check-circle"></i> Crear Cliente
                </button>
                <a href="${pageContext.request.contextPath}/clientes/list" class="btn btn-secondary">
                    <i class="bi bi-arrow-left"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</body>
</html>