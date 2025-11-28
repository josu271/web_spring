<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clientes - Techmerch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <div class="container">
        <h1><i class="bi bi-people"></i> Gestión de Clientes</h1>

        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/clientes/crear" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Nuevo Cliente
            </a>
        </div>

        <div class="table-container">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Correo</th>
                        <th>Celular</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${clientes}">
                        <tr>
                            <td>${cliente.dniCliente}</td>
                            <td>${cliente.nombre}</td>
                            <td>${cliente.apellido}</td>
                            <td>${cliente.correo}</td>
                            <td>${cliente.celular}</td>
                            <td>
                                <span class="status-badge status-${cliente.status}">
                                    ${cliente.status}
                                </span>
                            </td>
                            <td class="actions">

                                <a href="${pageContext.request.contextPath}/clientes/editar?dni=${cliente.dniCliente}"
                                   class="btn btn-warning btn-sm" title="Editar">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <c:if test="${cliente.status == 'ACTIVO'}">
                                    <form action="${pageContext.request.contextPath}/clientes/desactivar" method="post" style="display:inline;">
                                        <input type="hidden" name="dni" value="${cliente.dniCliente}">
                                        <button type="submit" class="btn btn-danger btn-sm" title="Desactivar">
                                            <i class="bi bi-x-circle"></i>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${cliente.status == 'INACTIVO'}">
                                    <form action="${pageContext.request.contextPath}/clientes/activar" method="post" style="display:inline;">
                                        <input type="hidden" name="dni" value="${cliente.dniCliente}">
                                        <button type="submit" class="btn btn-success btn-sm" title="Activar">
                                            <i class="bi bi-check-circle"></i>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>