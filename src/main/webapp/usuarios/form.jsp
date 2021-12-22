<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"/>

<h3 class="my-2">${ title }</h3>

<div class="row">
    <div class="col-12 col-md-10 col-lg-8 col-xl-6">
        <form action="${pageContext.request.contextPath}/usuarios/form" method="POST">
            <input type="hidden" name="id" value="${usuario.id}">
            <div class="mb-3 row">
                <label for="username" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="username" name="username" value="${usuario.username}" aria-describedby="usernameHelp">
                </div>
                <c:if test="${errores != null && not empty errores.username}">
                    <div id="usernameHelp" class="form-text" style="color: red;">${errores.username}</div>
                </c:if>
            </div>
            <div class="mb-3 row">
                <label for="password" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" value="${usuario.password}" aria-describedby="passwordHelp">
                </div>
                <c:if test="${errores != null && not empty errores.password}">
                    <div id="passwordHelp" class="form-text" style="color: red;">${errores.password}</div>
                </c:if>
            </div>
            <div class="mb-3 row">
                <label for="username" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="email" name="email" value="${usuario.email}" aria-describedby="emailHelp">
                </div>
                <c:if test="${errores != null && not empty errores.email}">
                    <div id="emailHelp" class="form-text" style="color: red;">${errores.email}</div>
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">
                ${usuario.id != null && usuario.id > 0 ? "Editar" : "Crear"}
            </button>
        </form>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>