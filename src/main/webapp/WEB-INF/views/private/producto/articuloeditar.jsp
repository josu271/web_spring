<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/articulo.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-section">
            <h1><i class="bi bi-pencil"></i> Editar Producto</h1>
            <a href="${pageContext.request.contextPath}/producto" class="btn-back">
                <i class="bi bi-arrow-left"></i> Volver a Productos
            </a>
        </div>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/producto/actualizar" method="post" class="product-form">
                <input type="hidden" name="idProducto" value="${producto.idProducto}">

                <div class="form-group">
                    <label for="nombre">Nombre del Producto: *</label>
                    <input type="text" id="nombre" name="nombre" value="${producto.nombre}"
                           required class="form-control">
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="3" class="form-control">${producto.descripcion}</textarea>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="idCategoria">Categoría: *</label>
                        <select id="idCategoria" name="idCategoria" required class="form-control">
                            <option value="">Seleccionar categoría</option>
                            <option value="1" ${producto.idCategoria == 1 ? 'selected' : ''}>Computadoras</option>
                            <option value="2" ${producto.idCategoria == 2 ? 'selected' : ''}>Laptops</option>
                            <option value="3" ${producto.idCategoria == 3 ? 'selected' : ''}>Componentes</option>
                            <option value="4" ${producto.idCategoria == 4 ? 'selected' : ''}>Periféricos</option>
                            <option value="5" ${producto.idCategoria == 5 ? 'selected' : ''}>Accesorios</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="tipoProducto">Tipo de Producto:</label>
                        <input type="text" id="tipoProducto" name="tipoProducto"
                               value="${producto.tipoProducto}" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label for="precio">Precio (S/.): *</label>
                    <input type="number" id="precio" name="precio" value="${producto.precio}"
                           step="0.01" min="0" required class="form-control">
                </div>

                <div class="form-group">
                    <label for="status">Estado:</label>
                    <select id="status" name="status" class="form-control">
                        <option value="ACTIVO" ${producto.status == 'ACTIVO' ? 'selected' : ''}>Activo</option>
                        <option value="INACTIVO" ${producto.status == 'INACTIVO' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-submit">
                        <i class="bi bi-check-circle"></i> Actualizar Producto
                    </button>
                    <a href="${pageContext.request.contextPath}/producto" class="btn-cancel">
                        <i class="bi bi-x-circle"></i> Cancelar
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>