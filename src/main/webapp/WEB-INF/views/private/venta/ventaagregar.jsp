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
            <!-- Mostrar errores -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <i class="bi bi-exclamation-triangle"></i> ${error}
                </div>
            </c:if>

            <!-- Mostrar éxito -->
            <c:if test="${not empty success}">
                <div class="alert alert-success">
                    <i class="bi bi-check-circle"></i> ${success}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/ventas/agregar" method="post" class="venta-form" id="ventaForm">
                <!-- CAMPOS OCULTOS PARA PRESERVAR BÚSQUEDAS -->
                <input type="hidden" name="busquedaCliente" id="hiddenBusquedaCliente" value="${busquedaCliente}">
                <input type="hidden" name="busquedaProducto" id="hiddenBusquedaProducto" value="${busquedaProducto}">

                <!-- Sección Cliente -->
                <div class="mb-4">
                    <h4 class="border-bottom pb-2 mb-3">
                        <i class="bi bi-person me-2"></i>Datos del Cliente
                    </h4>

                    <!-- Búsqueda de Cliente -->
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label">Buscar Cliente</label>
                            <div class="d-flex gap-2">
                                <input type="text" class="form-control" id="busquedaClienteInput"
                                       value="${busquedaCliente}" placeholder="Ingrese nombre o apellido del cliente">
                                <button type="button" class="btn btn-outline-primary" onclick="buscarCliente()">
                                    <i class="bi bi-search"></i> Buscar
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mt-4">
                                <a href="${pageContext.request.contextPath}/clientes/list" class="btn btn-outline-primary btn-sm">
                                    <i class="bi bi-plus-circle"></i> Nuevo Cliente
                                </a>
                                <c:if test="${not empty busquedaCliente}">
                                    <a href="${pageContext.request.contextPath}/ventas/agregar" class="btn btn-outline-secondary btn-sm">
                                        <i class="bi bi-x-circle"></i> Limpiar
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <!-- Select de Clientes -->
                    <div class="mb-3">
                        <label for="dniCliente" class="form-label">Seleccionar Cliente *</label>
                        <select class="form-select" id="dniCliente" name="dniCliente" required>
                            <option value="">Seleccione un cliente</option>
                            <c:choose>
                                <c:when test="${not empty clientesEncontrados}">
                                    <c:forEach var="cliente" items="${clientesEncontrados}">
                                        <option value="${cliente.dniCliente}"
                                                <c:if test="${venta.dniCliente == cliente.dniCliente}">selected</c:if>>
                                            ${cliente.nombre} ${cliente.apellido} - DNI: ${cliente.dniCliente}
                                        </option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="cliente" items="${clientes}">
                                        <option value="${cliente.dniCliente}"
                                                <c:if test="${venta.dniCliente == cliente.dniCliente}">selected</c:if>>
                                            ${cliente.nombre} ${cliente.apellido} - DNI: ${cliente.dniCliente}
                                        </option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>

                    <c:if test="${not empty busquedaCliente and empty clientesEncontrados}">
                        <div class="alert alert-warning">
                            <i class="bi bi-exclamation-triangle"></i> No se encontraron clientes con: "${busquedaCliente}"
                        </div>
                    </c:if>
                </div>

                <!-- Sección Empleado -->
                <div class="mb-4">
                    <h4 class="border-bottom pb-2 mb-3">
                        <i class="bi bi-person-badge me-2"></i>Datos del Vendedor
                    </h4>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="dniEmpleado" class="form-label">Empleado *</label>
                                <select class="form-select" id="dniEmpleado" name="dniEmpleado" required>
                                    <option value="">Seleccione un empleado</option>
                                    <c:forEach var="empleado" items="${empleados}">
                                        <option value="${empleado.dniEmpleado}"
                                                <c:if test="${venta.dniEmpleado == empleado.dniEmpleado}">selected</c:if>>
                                            ${empleado.nombre} ${empleado.apellido} - ${empleado.cargo}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Sección Productos -->
                <div class="mb-4">
                    <h4 class="border-bottom pb-2 mb-3">
                        <i class="bi bi-box-seam me-2"></i>Productos
                    </h4>

                    <!-- Búsqueda de Productos -->
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label">Buscar Producto</label>
                            <div class="d-flex gap-2">
                                <input type="text" class="form-control" id="busquedaProductoInput"
                                       value="${busquedaProducto}" placeholder="Ingrese nombre del producto">
                                <button type="button" class="btn btn-outline-primary" onclick="buscarProducto()">
                                    <i class="bi bi-search"></i> Buscar
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mt-4">
                                <c:if test="${not empty busquedaProducto}">
                                    <a href="${pageContext.request.contextPath}/ventas/agregar" class="btn btn-outline-secondary btn-sm">
                                        <i class="bi bi-x-circle"></i> Limpiar
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <!-- Lista de Productos -->
                    <c:if test="${not empty busquedaProducto}">
                        <c:choose>
                            <c:when test="${not empty productosEncontrados}">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover">
                                        <thead class="table-light">
                                            <tr>
                                                <th>Producto</th>
                                                <th>Precio</th>
                                                <th>Stock</th>
                                                <th>Cantidad</th>
                                                <th>Subtotal</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="producto" items="${productosEncontrados}">
                                                <tr>
                                                    <td>
                                                        <strong>${producto.nombre}</strong>
                                                        <c:if test="${not empty producto.descripcion}">
                                                            <br><small class="text-muted">${producto.descripcion}</small>
                                                        </c:if>
                                                    </td>
                                                    <td>S/. ${producto.precio}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${producto.stock > 0}">
                                                                <span class="badge bg-success">${producto.stock} unidades</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="badge bg-danger">Sin stock</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td style="width: 120px;">
                                                        <input type="number" name="cantidad_${producto.idProducto}"
                                                               class="form-control form-control-sm cantidad-input"
                                                               placeholder="0" min="0" max="${producto.stock}"
                                                               value="0" data-precio="${producto.precio}"
                                                               data-producto-id="${producto.idProducto}">
                                                    </td>
                                                    <td class="subtotal" id="subtotal_${producto.idProducto}">S/. 0.00</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr class="table-primary">
                                                <td colspan="4" class="text-end"><strong>TOTAL:</strong></td>
                                                <td><strong id="total-venta">S/. 0.00</strong></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-warning">
                                    <i class="bi bi-exclamation-triangle"></i> No se encontraron productos con: "${busquedaProducto}"
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <!-- Mensaje cuando no hay búsqueda de productos -->
                    <c:if test="${empty busquedaProducto}">
                        <div class="alert alert-info">
                            <i class="bi bi-info-circle"></i> Use la búsqueda para encontrar productos y agregarlos a la venta.
                        </div>
                    </c:if>
                </div>

                <!-- Sección Pago -->
                <div class="mb-4">
                    <h4 class="border-bottom pb-2 mb-3">
                        <i class="bi bi-credit-card me-2"></i>Información de Pago
                    </h4>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="metodoPago" class="form-label">Método de Pago *</label>
                                <select class="form-select" id="metodoPago" name="metodoPago" required>
                                    <option value="">Seleccione método</option>
                                    <option value="EFECTIVO" <c:if test="${venta.metodoPago == 'EFECTIVO'}">selected</c:if>>Efectivo</option>
                                    <option value="TARJETA" <c:if test="${venta.metodoPago == 'TARJETA'}">selected</c:if>>Tarjeta</option>
                                    <option value="TRANSFERENCIA" <c:if test="${venta.metodoPago == 'TRANSFERENCIA'}">selected</c:if>>Transferencia</option>
                                    <option value="YAPE" <c:if test="${venta.metodoPago == 'YAPE'}">selected</c:if>>Yape</option>
                                    <option value="PLIN" <c:if test="${venta.metodoPago == 'PLIN'}">selected</c:if>>Plin</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-success btn-lg" id="btnRegistrar">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
// Función para buscar cliente
function buscarCliente() {
    const busqueda = document.getElementById('busquedaClienteInput').value;
    const busquedaProducto = '${busquedaProducto}';

    let url = '${pageContext.request.contextPath}/ventas/agregar?busquedaCliente=' + encodeURIComponent(busqueda);
    if (busquedaProducto) {
        url += '&busquedaProducto=' + encodeURIComponent(busquedaProducto);
    }

    window.location.href = url;
}

// Función para buscar producto
function buscarProducto() {
    const busqueda = document.getElementById('busquedaProductoInput').value;
    const busquedaCliente = '${busquedaCliente}';

    let url = '${pageContext.request.contextPath}/ventas/agregar?busquedaProducto=' + encodeURIComponent(busqueda);
    if (busquedaCliente) {
        url += '&busquedaCliente=' + encodeURIComponent(busquedaCliente);
    }

    window.location.href = url;
}

// Calcular total automáticamente
document.addEventListener('DOMContentLoaded', function() {
    const cantidadInputs = document.querySelectorAll('.cantidad-input');

    function calcularTotal() {
        let total = 0;

        cantidadInputs.forEach(input => {
            const cantidad = parseInt(input.value) || 0;
            const precio = parseFloat(input.dataset.precio);
            const productoId = input.dataset.productoId;
            const subtotal = cantidad * precio;

            // Actualizar subtotal en la fila
            const subtotalCell = document.getElementById('subtotal_' + productoId);
            if (subtotalCell) {
                subtotalCell.textContent = 'S/. ' + subtotal.toFixed(2);
            }

            total += subtotal;
        });

        // Actualizar total general
        document.getElementById('total-venta').textContent = 'S/. ' + total.toFixed(2);
    }

    // Validar formulario antes de enviar
    document.getElementById('ventaForm').addEventListener('submit', function(e) {
        const cliente = document.getElementById('dniCliente').value;
        const empleado = document.getElementById('dniEmpleado').value;
        const metodoPago = document.getElementById('metodoPago').value;

        if (!cliente || !empleado || !metodoPago) {
            e.preventDefault();
            alert('Por favor, complete todos los campos obligatorios.');
            return;
        }

        // Verificar que haya al menos un producto seleccionado
        let productosConCantidad = 0;
        cantidadInputs.forEach(input => {
            if (parseInt(input.value) > 0) {
                productosConCantidad++;
            }
        });

        if (productosConCantidad === 0) {
            e.preventDefault();
            alert('Debe seleccionar al menos un producto con cantidad mayor a 0.');
            return;
        }

        // Mostrar confirmación
        if (!confirm('¿Está seguro de registrar esta venta?')) {
            e.preventDefault();
        }
    });

    // Agregar event listeners
    cantidadInputs.forEach(input => {
        input.addEventListener('input', calcularTotal);
        input.addEventListener('change', function() {
            if (this.value < 0) this.value = 0;
            if (this.value > parseInt(this.max)) this.value = this.max;
        });
    });

    // Calcular inicialmente
    calcularTotal();
});
</script>
</body>
</html>