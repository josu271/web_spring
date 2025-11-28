<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Citas Técnicas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>

<div class="contenedor">
    <%@ include file="../../layout/sidebar.jsp" %>

    <div class="contenido">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3">
                <i class="bi bi-calendar-check me-2"></i>Gestión de Citas Técnicas
            </h1>
            <a href="${pageContext.request.contextPath}/citastecnica/agregar" class="btn btn-primary">
                <i class="bi bi-plus-circle me-1"></i>Nueva Cita
            </a>
        </div>

        <div class="card">
            <div class="card-header">
                <h5 class="card-title mb-0">Lista de Citas Programadas</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty citasTecnicas}">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Cliente</th>
                                    <th>Técnico</th>
                                    <th>Servicio</th>
                                    <th>Estado</th>
                                    <th>Fecha Programada</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cita" items="${citasTecnicas}">
                                    <tr>
                                        <td>${cita.idCitaTecnica}</td>
                                        <td>${cita.dniCliente}</td>
                                        <td>${cita.dniEmpleado}</td>
                                        <td>${cita.servicio}</td>
                                        <td>
                                            <span class="badge
                                                <c:choose>
                                                    <c:when test="${cita.estado == 'Pendiente'}">bg-warning</c:when>
                                                    <c:when test="${cita.estado == 'En proceso'}">bg-info</c:when>
                                                    <c:when test="${cita.estado == 'Completado'}">bg-success</c:when>
                                                    <c:otherwise>bg-secondary</c:otherwise>
                                                </c:choose>">
                                                ${cita.estado}
                                            </span>
                                        </td>
                                        <td>${cita.fechaProgramada}</td>
                                        <td>
                                            <div class="btn-group btn-group-sm">
                                                <a href="${pageContext.request.contextPath}/citastecnica/editar/${cita.idCitaTecnica}"
                                                   class="btn btn-outline-primary" title="Editar cita">
                                                    <i class="bi bi-pencil"></i>
                                                </a>
                                                <form action="${pageContext.request.contextPath}/citastecnica/cambiar-estado"
                                                      method="post" class="d-inline">
                                                    <input type="hidden" name="id" value="${cita.idCitaTecnica}">
                                                    <select name="estado" onchange="this.form.submit()" class="form-select form-select-sm">
                                                        <option value="Pendiente" ${cita.estado == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
                                                        <option value="En proceso" ${cita.estado == 'En proceso' ? 'selected' : ''}>En proceso</option>
                                                        <option value="Completado" ${cita.estado == 'Completado' ? 'selected' : ''}>Completado</option>
                                                    </select>
                                                </form>
                                                <form action="${pageContext.request.contextPath}/citastecnica/eliminar/${cita.idCitaTecnica}"
                                                      method="post" class="d-inline">
                                                    <button type="submit" class="btn btn-outline-danger"
                                                            onclick="return confirm('¿Está seguro de eliminar esta cita?')"
                                                            title="Eliminar cita">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty citasTecnicas}">
                    <div class="alert alert-info text-center">
                        <i class="bi bi-info-circle me-2"></i>No hay citas técnicas registradas.
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

</body>
</html>