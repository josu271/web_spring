<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Techmerch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../../layout/navbar.jsp" />
<section class="login-section">
    <div class="login-container">
        <h1><i class="bi bi-laptop"></i> Techmerch</h1>
        <h2>Iniciar Sesión</h2>

        <%-- Mostrar mensaje de error si existe --%>
        <c:if test="${not empty error}">
            <div class="alert alert-error">
                <i class="bi bi-exclamation-triangle"></i> ${error}
            </div>
        </c:if>

        <%-- Formulario de login que envía a /login (POST) --%>
        <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="number" id="dni" name="dni" placeholder="Ingresa tu DNI" required>
            </div>

            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
            </div>

            <button type="submit" class="btn-login">
                <i class="bi bi-box-arrow-in-right"></i> Iniciar Sesión
            </button>
        </form>
        <p class="info-text">Solo personal autorizado</p>
    </div>
</section>

</body>
</html>