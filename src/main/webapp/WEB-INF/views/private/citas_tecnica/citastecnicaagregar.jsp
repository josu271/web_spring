<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Nueva Cita Técnica</title>
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
                <i class="bi bi-calendar-plus me-2"></i>Registrar Nueva Cita Técnica
            </h1>
            <a href="${pageContext.request.contextPath}/citastecnica/list" class="btn btn-secondary">
                <i class="bi bi-arrow-left me-1"></i>Volver a la lista
            </a>
        </div>

        <div class="card">
            <div class="card-header">
                <h5 class="card-title mb-0">Información de la Cita</h5>
            </div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/citastecnica/agregar">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="dniCliente" class="form-label">Cliente</label>
                            <select class="form-select" id="dniCliente" name="dniCliente" required>
                                <option value="">Seleccione un cliente</option>
                                <c:forEach var="cliente" items="${clientesActivos}">
                                    <option value="${cliente.dniCliente}">
                                        ${cliente.nombre} ${cliente.apellido} (${cliente.dniCliente})
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="form-text">Seleccione el cliente para la cita</div>
                        </div>
                        <div class="col-md-6">
                            <label for="dniEmpleado" class="form-label">Técnico Asignado</label>
                            <select class="form-select" id="dniEmpleado" name="dniEmpleado" required>
                                <option value="">Seleccione un técnico</option>
                                <c:forEach var="tecnico" items="${tecnicosActivos}">
                                    <option value="${tecnico.dniEmpleado}">
                                        ${tecnico.nombre} ${tecnico.apellido} - ${tecnico.cargo}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="form-text">Seleccione el técnico que atenderá la cita</div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="servicio" class="form-label">Tipo de Servicio</label>
                            <select class="form-select" id="servicio" name="servicio" required>
                                <option value="">Seleccione un servicio</option>
                                <option value="Reparación">Reparación</option>
                                <option value="Mantenimiento">Mantenimiento</option>
                                <option value="Instalación">Instalación</option>
                                <option value="Diagnóstico">Diagnóstico</option>
                                <option value="Actualización">Actualización</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="fechaProgramada" class="form-label">Fecha y Hora Programada</label>
                            <input type="datetime-local" class="form-control" id="fechaProgramada" name="fechaProgramada" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción del Servicio</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="4"
                                  placeholder="Describa los detalles del servicio a realizar..."></textarea>
                    </div>

                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="reset" class="btn btn-outline-secondary me-md-2">
                            <i class="bi bi-arrow-clockwise me-1"></i>Limpiar
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle me-1"></i>Guardar Cita
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>