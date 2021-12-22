<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"/>
    <h3>${ title }</h3>
    <form action="${pageContext.request.contextPath}/productos/form" method="POST">
        <input type="hidden" name="id" value="${producto.id}">
        <div class="row mb-2">
            <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
            <div class="col-sm-4">
                <input type="text" name="nombre" id="nombre" value="${producto.nombre}" class="form-control">
            </div>
            <c:if test="${errores != null && not empty errores.nombre}">
                <span style="color: red;">${errores.nombre}</span>
            </c:if>
        </div>
        <div class="row mb-2">
            <label for="precio" class="col-form-label col-sm-2">Precio</label>
            <div class="col-sm-4">
                <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio : ""}" class="form-control">
            </div>
            <c:if test="${errores != null && not empty errores.precio}">
                <span style="color: red;">${errores.precio}</span>
            </c:if>
        </div>
        <div class="row mb-2">
            <label for="sku" class="col-form-label col-sm-2">Sku</label>
            <div class="col-sm-4">
                <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
            </div>
            <c:if test="${errores != null && not empty errores.sku}">
                <span style="color: red;">${errores.sku}</span>
            </c:if>
        </div>
        <div class="row mb-2">
            <label for="fecha-registro" class="col-form-label col-sm-2">Fecha registro</label>
            <div class="col-sm-4">
                <input type="date" name="fecha-registro" id="fecha-registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}" class="form-control">
            </div>
            <c:if test="${errores != null && errores.containsKey('fecha-registro')}">
                <span style="color: red;">${errores.get("fecha-registro")}</span>
            </c:if>
        </div>
        <div class="row mb-2">
            <label for="categoria" class="col-form-label col-sm-2">Categoría</label>
            <div class="col-sm-4">
                <select name="categoria" id="categoria" class="form-select">
                    <option value="">-Seleccionar-</option>
                    <c:forEach items="${categorias}" var="c">
                        <option value="${c.id}" ${(c.id eq producto.categoria.id) ? "selected" : ""}>${c.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            <c:if test="${errores != null && not empty errores.categoria}">
                <span style="color: red;">${errores.categoria}</span>
            </c:if>
        </div>
        <div class="row mb-2">
            <div class="col-sm-6">
                <div class="d-grid grap-2">
                    <button type="submit" class="btn btn-primary">
                        ${(producto.id != null && producto.id > 0) ? "Editar" : "Crear"}
                    </button>
                </div>
            </div>
        </div> 
    </form>
<jsp:include page="layout/footer.jsp" />