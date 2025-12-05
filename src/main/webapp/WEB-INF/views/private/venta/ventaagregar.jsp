<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Techmerch - Nueva Venta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/venta.css">
    <style>
        .productos-multiples {
            border: 1px solid #dee2e6;
            border-radius: 0.375rem;
            padding: 20px;
            background-color: #f8f9fa;
        }
        .producto-seleccion {
            background-color: white;
            border: 1px solid #dee2e6;
            border-radius: 0.375rem;
            padding: 15px;
            margin-bottom: 15px;
        }
        .producto-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }
        .producto-info {
            font-size: 0.9rem;
            color: #6c757d;
            margin-bottom: 10px;
        }
        .producto-controls {
            display: flex;
            gap: 10px;
            align-items: center;
        }
        .total-container {
            background-color: #e9ecef;
            border-radius: 0.375rem;
            padding: 15px;
            margin-top: 20px;
        }
        .separator {
            border-top: 2px solid #dee2e6;
            margin: 25px 0;
        }
    </style>
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-container">
            <h1><i class="bi bi-cart-plus"></i> Nueva Venta</h1>
            <a href="${pageContext.request.contextPath}/ventas/list" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver
            </a>
        </div>

        <div class="form-container">
            <!-- Mostrar mensajes -->
            <c:if test="${not empty success}">
                <div class="alert alert-success alert-dismissible fade show">
                    <i class="bi bi-check-circle"></i> ${success}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show">
                    <i class="bi bi-exclamation-triangle"></i> ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- SECCIÓN 1: Formulario para agregar productos (FUERA del form principal) -->
            <div class="mb-4">
                <h4 class="border-bottom pb-2 mb-3">
                    <i class="bi bi-plus-circle me-2"></i>Agregar Productos al Carrito
                </h4>

                <div class="p-3 bg-light rounded">
                    <form action="${pageContext.request.contextPath}/ventas/agregar-producto" method="post" class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Seleccionar Producto</label>
                            <select class="form-select" name="productoId" required>
                                <option value="">Seleccione un producto</option>
                                <c:forEach var="producto" items="${productos}">
                                    <c:if test="${producto.stock > 0}">
                                        <option value="${producto.idProducto}">
                                            ${producto.nombre} - S/. ${producto.precio} (Stock: ${producto.stock})
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Cantidad</label>
                            <input type="number" class="form-control" name="cantidad"
                                   min="1" value="1" required>
                        </div>
                        <div class="col-md-2 d-flex align-items-end">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-cart-plus"></i> Agregar al Carrito
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="separator"></div>

            <!-- SECCIÓN 2: Productos en el carrito -->
            <div class="mb-4">
                <h4 class="border-bottom pb-2 mb-3">
                    <i class="bi bi-cart-check me-2"></i>Productos en el Carrito
                </h4>

                <div class="productos-multiples">
                    <c:choose>
                        <c:when test="${not empty productosSeleccionados}">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h6 class="mb-0"><i class="bi bi-check-circle"></i> ${productosSeleccionados.size()} producto(s) seleccionado(s)</h6>
                                <a href="${pageContext.request.contextPath}/ventas/limpiar-carrito"
                                   class="btn btn-outline-danger btn-sm">
                                    <i class="bi bi-trash"></i> Vaciar Carrito
                                </a>
                            </div>

                            <c:set var="totalVenta" value="0" />

                            <c:forEach var="entry" items="${productosSeleccionados}">
                                <c:set var="producto" value="${entry.value}" />
                                <c:set var="cantidad" value="${cantidades[entry.key]}" />
                                <c:set var="subtotal" value="${producto.precio * cantidad}" />
                                <c:set var="totalVenta" value="${totalVenta + subtotal}" />

                                <div class="producto-seleccion">
                                    <div class="producto-header">
                                        <h6 class="mb-0">
                                            <i class="bi bi-box"></i> ${producto.nombre}
                                        </h6>
                                        <span class="badge bg-success">S/. ${producto.precio}</span>
                                    </div>

                                    <div class="producto-info">
                                        <c:if test="${not empty producto.descripcion}">
                                            <p class="mb-1">${producto.descripcion}</p>
                                        </c:if>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <p class="mb-1"><strong>Stock disponible:</strong> ${producto.stock} unidades</p>
                                            </div>
                                            <div class="col-md-6">
                                                <p class="mb-1"><strong>Cantidad:</strong> ${cantidad}</p>
                                            </div>
                                        </div>
                                        <p class="mb-0"><strong>Subtotal:</strong> S/. ${subtotal}</p>
                                    </div>

                                    <div class="producto-controls">
                                        <!-- Formulario para remover producto (INDEPENDIENTE) -->
                                        <form action="${pageContext.request.contextPath}/ventas/remover-producto"
                                              method="post" style="display: inline;">
                                            <input type="hidden" name="productoId" value="${producto.idProducto}">
                                            <button type="submit" class="btn btn-danger btn-sm">
                                                <i class="bi bi-trash"></i> Quitar del Carrito
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>

                            <!-- Total de la venta -->
                            <div class="total-container">
                                <div class="row">
                                    <div class="col-md-8 text-end">
                                        <h5 class="mb-0">TOTAL DEL CARRITO:</h5>
                                    </div>
                                    <div class="col-md-4">
                                        <h4 class="text-primary mb-0">S/. ${totalVenta}</h4>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center text-muted py-4">
                                <i class="bi bi-cart-x" style="font-size: 3rem;"></i>
                                <h5 class="mt-3">Carrito vacío</h5>
                                <p class="mt-2">Agrega productos usando el formulario de arriba</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="separator"></div>

            <!-- SECCIÓN 3: Formulario principal para la venta (SOLO datos de venta) -->
            <div class="mb-4">
                <h4 class="border-bottom pb-2 mb-3">
                    <i class="bi bi-receipt me-2"></i>Datos de la Venta
                </h4>

                <form action="${pageContext.request.contextPath}/ventas/agregar" method="post" class="venta-form">
                    <div class="row">
                        <!-- Cliente -->
                        <div class="col-md-6 mb-3">
                            <label for="dniCliente" class="form-label">Cliente *</label>
                            <select class="form-select" id="dniCliente" name="dniCliente" required>
                                <option value="">Seleccione un cliente</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.dniCliente}">
                                        ${cliente.nombre} ${cliente.apellido} - DNI: ${cliente.dniCliente}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="form-text">
                                <a href="${pageContext.request.contextPath}/clientes/list" class="text-decoration-none">
                                    <i class="bi bi-plus-circle"></i> Crear nuevo cliente
                                </a>
                            </div>
                        </div>

                        <!-- Empleado -->
                        <div class="col-md-6 mb-3">
                            <label for="dniEmpleado" class="form-label">Vendedor *</label>
                            <select class="form-select" id="dniEmpleado" name="dniEmpleado" required>
                                <option value="">Seleccione un empleado</option>
                                <c:forEach var="empleado" items="${empleados}">
                                    <option value="${empleado.dniEmpleado}">
                                        ${empleado.nombre} ${empleado.apellido} - ${empleado.cargo}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <!-- Método de Pago -->
                        <div class="col-md-6 mb-3">
                            <label for="metodoPago" class="form-label">Método de Pago *</label>
                            <select class="form-select" id="metodoPago" name="metodoPago" required>
                                <option value="">Seleccione método</option>
                                <option value="EFECTIVO">Efectivo</option>
                                <option value="TARJETA">Tarjeta</option>
                                <option value="TRANSFERENCIA">Transferencia</option>
                                <option value="YAPE">Yape</option>
                                <option value="PLIN">Plin</option>
                            </select>
                        </div>

                        <!-- Observaciones (opcional) -->
                        <div class="col-md-6 mb-3">
                            <label for="observaciones" class="form-label">Observaciones</label>
                            <textarea class="form-control" id="observaciones" name="observaciones"
                                      rows="2" placeholder="Notas adicionales..."></textarea>
                        </div>
                    </div>

                    <!-- Resumen del carrito (solo lectura) -->
                    <c:if test="${not empty productosSeleccionados}">
                        <div class="mb-3 p-3 bg-light rounded">
                            <h6 class="mb-2"><i class="bi bi-list-check"></i> Resumen del Pedido</h6>
                            <div class="small">
                                <c:forEach var="entry" items="${productosSeleccionados}">
                                    <c:set var="producto" value="${entry.value}" />
                                    <c:set var="cantidad" value="${cantidades[entry.key]}" />
                                    <div class="d-flex justify-content-between">
                                        <span>${producto.nombre} x${cantidad}</span>
                                        <span>S/. ${producto.precio * cantidad}</span>
                                    </div>
                                </c:forEach>
                                <hr class="my-1">
                                <div class="d-flex justify-content-between fw-bold">
                                    <span>TOTAL:</span>
                                    <span>S/. ${totalVenta}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Validación de carrito vacío -->
                    <c:if test="${empty productosSeleccionados}">
                        <div class="alert alert-warning">
                            <i class="bi bi-exclamation-triangle"></i>
                            No se puede registrar la venta porque el carrito está vacío.
                            Agrega productos primero.
                        </div>
                    </c:if>

                    <div class="form-actions mt-4">
                        <button type="submit" class="btn btn-success btn-lg"
                                ${empty productosSeleccionados ? 'disabled' : ''}>
                            <i class="bi bi-check-circle"></i> Registrar Venta
                        </button>
                        <a href="${pageContext.request.contextPath}/ventas/list" class="btn btn-secondary">
                            <i class="bi bi-x-circle"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>