<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Cita Técnica</title>
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
                <i class="bi bi-pencil-square me-2"></i>Editar Cita Técnica
            </h1>
            <a href="${pageContext.request.contextPath}/citastecnica/list" class="btn btn-secondary">
                <i class="bi bi-arrow-left me-1"></i>Volver a la lista
            </a>
        </div>

        <div class="card">
            <div class="card-header">
                <h5 class="card-title mb-0">Modificar Información de la Cita</h5>
            </div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/citastecnica/editar">
                    <input type="hidden" name="idCitaTecnica" value="${citaTecnica.idCitaTecnica}">

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="dniCliente" class="form-label">DNI del Cliente</label>
                            <input type="number" class="form-control" id="dniCliente" name="dniCliente"
                                   value="${citaTecnica.dniCliente}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="dniEmpleado" class="form-label">DNI del Técnico</label>
                            <input type="number" class="form-control" id="dniEmpleado" name="dniEmpleado"
                                   value="${citaTecnica.dniEmpleado}" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="servicio" class="form-label">Tipo de Servicio</label>
                            <select class="form-select" id="servicio" name="servicio" required>
                                <option value="Reparación" ${citaTecnica.servicio == 'Reparación' ? 'selected' : ''}>Reparación</option>
                                <option value="Mantenimiento" ${citaTecnica.servicio == 'Mantenimiento' ? 'selected' : ''}>Mantenimiento</option>
                                <option value="Instalación" ${citaTecnica.servicio == 'Instalación' ? 'selected' : ''}>Instalación</option>
                                <option value="Diagnóstico" ${citaTecnica.servicio == 'Diagnóstico' ? 'selected' : ''}>Diagnóstico</option>
                                <option value="Actualización" ${citaTecnica.servicio == 'Actualización' ? 'selected' : ''}>Actualización</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="fechaProgramada" class="form-label">Fecha y Hora Programada</label>
                            <input type="datetime-local" class="form-control" id="fechaProgramada" name="fechaProgramada"
                                   value="${citaTecnica.fechaProgramada}" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción del Servicio</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="4">${citaTecnica.descripcion}</textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Estado Actual</label>
                        <div>
                            <span class="badge
                                <c:choose>
                                    <c:when test="${citaTecnica.estado == 'Pendiente'}">bg-warning</c:when>
                                    <c:when test="${citaTecnica.estado == 'En proceso'}">bg-info</c:when>
                                    <c:when test="${citaTecnica.estado == 'Completado'}">bg-success</c:when>
                                    <c:otherwise>bg-secondary</c:otherwise>
                                </c:choose> fs-6">
                                ${citaTecnica.estado}
                            </span>
                        </div>
                        <div class="form-text">Para cambiar el estado, use la lista principal de citas</div>
                    </div>

                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <a href="${pageContext.request.contextPath}/citastecnica/list" class="btn btn-outline-secondary me-md-2">
                            <i class="bi bi-x-circle me-1"></i>Cancelar
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle me-1"></i>Actualizar Cita
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>