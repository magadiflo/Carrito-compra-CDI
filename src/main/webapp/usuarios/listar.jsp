<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"/>

<h3>${ title }</h3>
<c:if test="${username.present}">
    <div class="my-2">
        <a href="${pageContext.request.contextPath}/usuarios/form" class="btn btn-primary">Crear [+]</a>
    </div>
</c:if>
<div class="row">
    <div class="col-12 col-md-10 col-lg-8 col-xl-6">
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>email</th>
                    <c:if test="${username.present}">
                        <th>editar</th>
                        <th>eliminar</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.username}</td>
                        <td>${usuario.email}</td>
                        <c:if test="${username.present}">
                            <td><a href="${pageContext.request.contextPath}/usuarios/form?id=${usuario.id}" class="btn btn-warning btn-sm">editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/usuarios/eliminar?id=${usuario.id}" class="btn btn-danger btn-sm" onclick="javascript:return confirm('¿Seguro que dese eliminar el usuario?')">eliminar</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../layout/footer.jsp"/>