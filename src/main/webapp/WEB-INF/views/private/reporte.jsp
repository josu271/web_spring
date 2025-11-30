<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - TiendaTech</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reporte.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="d-flex">
    <%@ include file="../layout/sidebar.jsp" %>

    <!-- Contenido principal -->
    <div class="content p-4 w-100">
        <h2 class="mb-4"><i class="bi bi-speedometer2"></i> Dashboard</h2>

        <!-- Resumen rápido -->
        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card text-center shadow-sm stats-card">
                    <div class="card-body">
                        <h5 class="card-title">Ventas del Día</h5>
                        <p class="fs-4">$<fmt:formatNumber value="${ventasDelDia != null ? ventasDelDia : 0}" pattern="#,##0.00"/></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-center shadow-sm stats-card">
                    <div class="card-body">
                        <h5 class="card-title">Ventas Semanales</h5>
                        <p class="fs-4">$<fmt:formatNumber value="${ventasDeLaSemana != null ? ventasDeLaSemana : 0}" pattern="#,##0.00"/></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-center shadow-sm stats-card">
                    <div class="card-body">
                        <h5 class="card-title">Ventas Mensuales</h5>
                        <p class="fs-4">$<fmt:formatNumber value="${ventasDelMes != null ? ventasDelMes : 0}" pattern="#,##0.00"/></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-center shadow-sm stats-card">
                    <div class="card-body">
                        <h5 class="card-title">Promedio Venta</h5>
                        <p class="fs-4">$<fmt:formatNumber value="${promedioVenta != null ? promedioVenta : 0}" pattern="#,##0.00"/></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Segunda fila de estadísticas -->
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-warning"><i class="bi bi-clock"></i> Citas Pendientes</h5>
                        <p class="fs-4 text-warning">${citasPendientes != null ? citasPendientes : 0}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-info"><i class="bi bi-gear"></i> Citas en Progreso</h5>
                        <p class="fs-4 text-info">${citasEnProgreso != null ? citasEnProgreso : 0}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-success"><i class="bi bi-check-circle"></i> Citas Completadas</h5>
                        <p class="fs-4 text-success">${citasCompletadas != null ? citasCompletadas : 0}</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Gráficos -->
        <div class="row">
            <div class="col-md-6 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Ventas de los Últimos 7 Días</h5>
                        <div class="chart-container">
                            <canvas id="ventasChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Top 5 Productos Más Vendidos</h5>
                        <div class="chart-container">
                            <canvas id="productosChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Categorías Más Vendidas</h5>
                        <div class="chart-container">
                            <canvas id="categoriasChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    // Gráfico de ventas semanales
    const ventasSemanales = [
        <c:choose>
            <c:when test="${not empty ventasSemanales}">
                <c:forEach var="venta" items="${ventasSemanales}" varStatus="status">
                    ${venta.totalVentas != null ? venta.totalVentas : 0}${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>0,0,0,0,0,0,0</c:otherwise>
        </c:choose>
    ];

    const fechasSemanales = [
        <c:choose>
            <c:when test="${not empty ventasSemanales}">
                <c:forEach var="venta" items="${ventasSemanales}" varStatus="status">
                    '${venta.fecha != null ? venta.fecha : "Fecha"}'${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>'Lun','Mar','Mié','Jue','Vie','Sáb','Dom'</c:otherwise>
        </c:choose>
    ];

    // Gráfico de top productos
    const topProductosLabels = [
        <c:choose>
            <c:when test="${not empty topProductos}">
                <c:forEach var="producto" items="${topProductos}" varStatus="status">
                    '${producto.nombreProducto != null ? producto.nombreProducto : "Producto"}'${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>'Producto 1','Producto 2','Producto 3','Producto 4','Producto 5'</c:otherwise>
        </c:choose>
    ];

    const topProductosData = [
        <c:choose>
            <c:when test="${not empty topProductos}">
                <c:forEach var="producto" items="${topProductos}" varStatus="status">
                    ${producto.cantidadVendida != null ? producto.cantidadVendida : 0}${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>0,0,0,0,0</c:otherwise>
        </c:choose>
    ];

    // Gráfico de categorías
    const categoriasLabels = [
        <c:choose>
            <c:when test="${not empty categoriasVendidas}">
                <c:forEach var="categoria" items="${categoriasVendidas}" varStatus="status">
                    '${categoria.nombreCategoria != null ? categoria.nombreCategoria : "Categoría"}'${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>'Computadoras','Accesorios','Celulares','Otros'</c:otherwise>
        </c:choose>
    ];

    const categoriasData = [
        <c:choose>
            <c:when test="${not empty categoriasVendidas}">
                <c:forEach var="categoria" items="${categoriasVendidas}" varStatus="status">
                    ${categoria.cantidadVendida != null ? categoria.cantidadVendida : 0}${!status.last ? ',' : ''}
                </c:forEach>
            </c:when>
            <c:otherwise>0,0,0,0</c:otherwise>
        </c:choose>
    ];

    // Inicializar gráficos después de que la página cargue
    document.addEventListener('DOMContentLoaded', function() {
        // Gráfico de ventas
        new Chart(document.getElementById('ventasChart'), {
            type: 'line',
            data: {
                labels: fechasSemanales,
                datasets: [{
                    label: 'Ventas ($)',
                    data: ventasSemanales,
                    borderColor: '#1e40af',
                    backgroundColor: 'rgba(30, 64, 175, 0.2)',
                    fill: true,
                    tension: 0.3
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });

        // Gráfico de top productos
        new Chart(document.getElementById('productosChart'), {
            type: 'bar',
            data: {
                labels: topProductosLabels,
                datasets: [{
                    label: 'Cantidad Vendida',
                    data: topProductosData,
                    backgroundColor: ['#1e3a8a', '#16a34a', '#f59e0b', '#dc2626', '#7c3aed']
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });

        // Gráfico de categorías
        new Chart(document.getElementById('categoriasChart'), {
            type: 'doughnut',
            data: {
                labels: categoriasLabels,
                datasets: [{
                    data: categoriasData,
                    backgroundColor: ['#1e3a8a', '#16a34a', '#f59e0b', '#6b7280', '#dc2626', '#7c3aed']
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
    });
</script>
</body>
</html>