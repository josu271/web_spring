<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/articulo.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-section">
            <h1><i class="bi bi-box-seam"></i> Gestión de Productos</h1>
            <a href="${pageContext.request.contextPath}/producto/agregar" class="btn-add">
                <i class="bi bi-plus-circle"></i> Agregar Producto
            </a>
        </div>

        <c:if test="${not empty message}">
            <div class="alert alert-success">
                ${message}
            </div>
        </c:if>

        <div class="table-container">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Tipo</th>
                        <th>Precio</th>
                        <!-- Eliminar columna Stock -->
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="producto" items="${productos}">
                        <tr>
                            <td>${producto.idProducto}</td>
                            <td>${producto.nombre}</td>
                            <td>${producto.descripcion}</td>
                            <td>${producto.tipoProducto}</td>
                            <td>S/. ${producto.precio}</td>
                            <!-- Eliminar celda de stock -->
                            <td>
                                <span class="status-badge status-${producto.status}">
                                    ${producto.status}
                                </span>
                            </td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/producto/editar?id=${producto.idProducto}"
                                   class="btn-edit" title="Editar">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <c:if test="${producto.status == 'ACTIVO'}">
                                    <a href="${pageContext.request.contextPath}/producto/desactivar?id=${producto.idProducto}"
                                       class="btn-deactivate" title="Desactivar"
                                       onclick="return confirm('¿Está seguro de desactivar este producto?')">
                                        <i class="bi bi-eye-slash"></i>
                                    </a>
                                </c:if>
                                <c:if test="${producto.status == 'INACTIVO'}">
                                    <a href="${pageContext.request.contextPath}/producto/activar?id=${producto.idProducto}"
                                       class="btn-activate" title="Activar"
                                       onclick="return confirm('¿Está seguro de activar este producto?')">
                                        <i class="bi bi-eye"></i>
                                    </a>
                                </c:if>
                                <a href="${pageContext.request.contextPath}/producto/eliminar?id=${producto.idProducto}"
                                   class="btn-delete" title="Eliminar"
                                   onclick="return confirm('¿Está seguro de eliminar este producto?')">
                                    <i class="bi bi-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty productos}">
                        <tr>
                            <td colspan="7" class="no-data">No hay productos registrados</td> <!-- Cambiar de 8 a 7 -->
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>