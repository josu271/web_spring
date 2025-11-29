<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Techmerch - Editar Venta</title>
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
            <h1><i class="bi bi-cart-check"></i> Editar Venta</h1>
            <a href="${pageContext.request.contextPath}/ventas/list" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver
            </a>
        </div>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/ventas/editar" method="post" class="venta-form">
                <input type="hidden" name="idVenta" value="${venta.idVenta}">

                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="dniCliente" class="form-label">Cliente *</label>
                            <select class="form-select" id="dniCliente" name="dniCliente" required>
                                <option value="">Seleccione un cliente</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.dniCliente}" ${venta.dniCliente == cliente.dniCliente ? 'selected' : ''}>
                                        ${cliente.nombre} ${cliente.apellido} - DNI: ${cliente.dniCliente}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="dniEmpleado" class="form-label">Empleado *</label>
                            <select class="form-select" id="dniEmpleado" name="dniEmpleado" required>
                                <option value="">Seleccione un empleado</option>
                                <c:forEach var="empleado" items="${empleados}">
                                    <option value="${empleado.dniEmpleado}" ${venta.dniEmpleado == empleado.dniEmpleado ? 'selected' : ''}>
                                        ${empleado.nombre} ${empleado.apellido} - ${empleado.cargo}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="metodoPago" class="form-label">Método de Pago *</label>
                            <select class="form-select" id="metodoPago" name="metodoPago" required>
                                <option value="">Seleccione método</option>
                                <option value="EFECTIVO" ${venta.metodoPago == 'EFECTIVO' ? 'selected' : ''}>Efectivo</option>
                                <option value="TARJETA" ${venta.metodoPago == 'TARJETA' ? 'selected' : ''}>Tarjeta</option>
                                <option value="TRANSFERENCIA" ${venta.metodoPago == 'TRANSFERENCIA' ? 'selected' : ''}>Transferencia</option>
                                <option value="YAPE" ${venta.metodoPago == 'YAPE' ? 'selected' : ''}>Yape</option>
                                <option value="PLIN" ${venta.metodoPago == 'PLIN' ? 'selected' : ''}>Plin</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="total" class="form-label">Total (S/.) *</label>
                            <input type="number" step="0.01" class="form-control" id="total" name="total"
                                   value="${venta.total}" required placeholder="0.00" min="0">
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Estado</label>
                    <select class="form-select" id="status" name="status">
                        <option value="COMPLETADA" ${venta.status == 'COMPLETADA' ? 'selected' : ''}>Completada</option>
                        <option value="ANULADA" ${venta.status == 'ANULADA' ? 'selected' : ''}>Anulada</option>
                        <option value="PENDIENTE" ${venta.status == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                    </select>
                </div>

                <div class="form-info">
                    <p><strong>Fecha de Venta:</strong> ${venta.fechaVenta}</p>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-check-circle"></i> Actualizar Venta
                    </button>
                    <a href="${pageContext.request.contextPath}/ventas/list" class="btn btn-secondary">
                        <i class="bi bi-x-circle"></i> Cancelar
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>