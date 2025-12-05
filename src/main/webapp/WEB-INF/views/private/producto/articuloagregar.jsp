<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/articulo.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="header-section">
            <h1><i class="bi bi-plus-circle"></i> Agregar Nuevo Producto</h1>
            <a href="${pageContext.request.contextPath}/producto" class="btn-back">
                <i class="bi bi-arrow-left"></i> Volver a Productos
            </a>
        </div>

        <div class="form-container">
            <form action="${pageContext.request.contextPath}/producto/crear" method="post" class="product-form">
                <div class="form-group">
                    <label for="nombre">Nombre del Producto: *</label>
                    <input type="text" id="nombre" name="nombre" required class="form-control"
                           placeholder="Ingrese el nombre del producto">
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="3" class="form-control"
                              placeholder="Ingrese una descripción del producto"></textarea>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="idCategoria">Categoría: *</label>
                        <select id="idCategoria" name="idCategoria" required class="form-control">
                            <option value="">Seleccionar categoría</option>
                            <option value="1">Computadoras</option>
                            <option value="2">Laptops</option>
                            <option value="3">Componentes</option>
                            <option value="4">Periféricos</option>
                            <option value="5">Accesorios</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="tipoProducto">Tipo de Producto:</label>
                        <input type="text" id="tipoProducto" name="tipoProducto" class="form-control"
                               placeholder="Ej: Gamer, Oficina, etc.">
                    </div>
                </div>

                <div class="form-group"> <!-- Cambiar de form-row a form-group simple -->
                    <label for="precio">Precio (S/.): *</label>
                    <input type="number" id="precio" name="precio" step="0.01" min="0"
                           required class="form-control" placeholder="0.00">
                </div>

                <!-- Eliminar campo Stock -->
                <!--
                <div class="form-group">
                    <label for="stock">Stock: *</label>
                    <input type="number" id="stock" name="stock" min="0" required
                           class="form-control" placeholder="0">
                </div>
                -->

                <div class="form-actions">
                    <button type="submit" class="btn-submit">
                        <i class="bi bi-check-circle"></i> Guardar Producto
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