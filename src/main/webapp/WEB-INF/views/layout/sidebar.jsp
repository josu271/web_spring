<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>TecMerch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

<aside class="sidebar">
  <div class="logo">
    <i class="bi bi-laptop"></i>
    <span>TiendaTech</span>
  </div>
  <nav>
    <a href="${pageContext.request.contextPath}/ventas/list"><i class="bi bi-cart"></i> Ventas</a>
    <a href="${pageContext.request.contextPath}/producto"><i class="bi bi-box-seam"></i> Productos</a>
    <a href="${pageContext.request.contextPath}/reporte"><i class="bi bi-file-earmark-bar-graph"></i> Reportes</a>
    <a href="${pageContext.request.contextPath}/citastecnica/list"><i class="bi bi-tools"></i> Citas Técnicas</a>
    <a href="${pageContext.request.contextPath}/clientes/list"><i class="bi bi-people"></i> Clientes</a>
  </nav>
</aside>

</body>
</html>