<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Techmerch - Ventas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/venta.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-container">
            <h1><i class="bi bi-cart-check"></i> Gestión de Ventas</h1>
            <a href="${pageContext.request.contextPath}/ventas/agregar" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Nueva Venta
            </a>
        </div>

        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Empleado</th>
                        <th>Fecha</th>
                        <th>Método Pago</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="venta" items="${ventas}">
                        <tr>
                            <td>${venta.idVenta}</td>
                            <td>${venta.dniCliente}</td>
                            <td>${venta.dniEmpleado}</td>
                            <td>${venta.fechaVenta}</td>
                            <td>
                                <span class="badge bg-info">${venta.metodoPago}</span>
                            </td>
                            <td>
                                <strong>S/. ${venta.total}</strong>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${venta.status == 'COMPLETADA'}">
                                        <span class="badge bg-success">${venta.status}</span>
                                    </c:when>
                                    <c:when test="${venta.status == 'ANULADA'}">
                                        <span class="badge bg-danger">${venta.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">${venta.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <a href="${pageContext.request.contextPath}/ventas/editar/${venta.idVenta}"
                                       class="btn btn-sm btn-warning">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <c:if test="${venta.status != 'ANULADA'}">
                                        <a href="${pageContext.request.contextPath}/ventas/eliminar/${venta.idVenta}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('¿Está seguro de anular esta venta?')">
                                            <i class="bi bi-x-circle"></i>
                                        </a>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty ventas}">
                        <tr>
                            <td colspan="8" class="text-center text-muted">
                                <i class="bi bi-inbox"></i> No hay ventas registradas
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>