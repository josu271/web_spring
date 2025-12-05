<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Venta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/articulo.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-section">
            <h1><i class="bi bi-cart-plus"></i> Registrar Nueva Venta</h1>
            <a href="${pageContext.request.contextPath}/ventas" class="btn-back">
                <i class="bi bi-arrow-left"></i> Volver a Ventas
            </a>
        </div>

        <!-- Mensajes de éxito/error -->
        <c:if test="${not empty success}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${success}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <div class="row">
            <!-- Formulario principal -->
            <div class="col-md-8">
                <div class="card mb-4">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Información de la Venta</h5>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/ventas/agregar" method="post">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="dniCliente" class="form-label">Cliente *</label>
                                    <select class="form-select" id="dniCliente" name="dniCliente" required>
                                        <option value="">Seleccione un cliente</option>
                                        <c:forEach var="cliente" items="${clientes}">
                                            <option value="${cliente.dniCliente}">
                                                ${cliente.nombre} ${cliente.apellido}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="dniEmpleado" class="form-label">Empleado *</label>
                                    <select class="form-select" id="dniEmpleado" name="dniEmpleado" required>
                                        <option value="">Seleccione un empleado</option>
                                        <c:forEach var="empleado" items="${empleados}">
                                            <option value="${empleado.dniEmpleado}">
                                                ${empleado.nombre} ${empleado.apellido}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="metodoPago" class="form-label">Método de Pago *</label>
                                    <select class="form-select" id="metodoPago" name="metodoPago" required>
                                        <option value="">Seleccione método</option>
                                        <option value="EFECTIVO">Efectivo</option>
                                        <option value="TARJETA">Tarjeta</option>
                                        <option value="TRANSFERENCIA">Transferencia</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="status" class="form-label">Estado</label>
                                    <select class="form-select" id="status" name="status">
                                        <option value="COMPLETADA" selected>Completada</option>
                                        <option value="PENDIENTE">Pendiente</option>
                                        <option value="CANCELADA">Cancelada</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Productos seleccionados -->
                            <div class="mb-4">
                                <h6>Productos Seleccionados:</h6>
                                <c:if test="${empty productosSeleccionados}">
                                    <div class="alert alert-info">No hay productos seleccionados</div>
                                </c:if>
                                <c:if test="${not empty productosSeleccionados}">
                                    <div class="table-responsive">
                                        <table class="table table-sm">
                                            <thead>
                                                <tr>
                                                    <th>Producto</th>
                                                    <th>Precio Unit.</th>
                                                    <th>Cantidad</th>
                                                    <th>Subtotal</th>
                                                    <th>Acción</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="totalVenta" value="0" />
                                                <c:forEach var="entry" items="${productosSeleccionados}">
                                                    <c:set var="producto" value="${entry.value}" />
                                                    <c:set var="productoId" value="${entry.key}" />
                                                    <c:set var="cantidad" value="${cantidades[productoId]}" />
                                                    <c:set var="subtotal" value="${producto.precio * cantidad}" />
                                                    <c:set var="totalVenta" value="${totalVenta + subtotal}" />
                                                    <tr>
                                                        <td>${producto.nombre}</td>
                                                        <td>S/. ${producto.precio}</td>
                                                        <td>${cantidad}</td>
                                                        <td>S/. ${subtotal}</td>
                                                        <td>
                                                            <form action="${pageContext.request.contextPath}/ventas/remover-producto"
                                                                  method="post" style="display: inline;">
                                                                <input type="hidden" name="productoId" value="${productoId}">
                                                                <button type="submit" class="btn btn-sm btn-danger">
                                                                    <i class="bi bi-trash"></i>
                                                                </button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th colspan="3" class="text-end">Total:</th>
                                                    <th>S/. ${totalVenta}</th>
                                                    <th></th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </c:if>
                            </div>

                            <div class="d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/ventas/limpiar-carrito"
                                   class="btn btn-warning">
                                    <i class="bi bi-cart-x"></i> Limpiar Carrito
                                </a>
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-check-circle"></i> Finalizar Venta
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Agregar productos -->
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Agregar Productos</h5>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/ventas/agregar-producto" method="post">
                            <div class="mb-3">
                                <label for="productoId" class="form-label">Producto</label>
                                <select class="form-select" id="productoId" name="productoId" required>
                                    <option value="">Seleccione un producto</option>
                                    <c:forEach var="producto" items="${productos}">
                                        <c:if test="${producto.status == 'ACTIVO'}">
                                            <option value="${producto.idProducto}">
                                                ${producto.nombre} - S/. ${producto.precio}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="cantidad" class="form-label">Cantidad</label>
                                <input type="number" class="form-control" id="cantidad"
                                       name="cantidad" min="1" value="1" required>
                            </div>
                            <button type="submit" class="btn btn-success w-100">
                                <i class="bi bi-cart-plus"></i> Agregar al Carrito
                            </button>
                        </form>

                        <!-- Lista de productos disponibles -->
                        <hr>
                        <h6>Productos Disponibles:</h6>
                        <div class="list-group" style="max-height: 300px; overflow-y: auto;">
                            <c:forEach var="producto" items="${productos}">
                                <c:if test="${producto.status == 'ACTIVO'}">
                                    <div class="list-group-item">
                                        <div class="d-flex w-100 justify-content-between">
                                            <h6 class="mb-1">${producto.nombre}</h6>
                                            <small>S/. ${producto.precio}</small>
                                        </div>
                                        <p class="mb-1 text-muted">${producto.descripcion}</p>
                                        <small>Tipo: ${producto.tipoProducto}</small>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>