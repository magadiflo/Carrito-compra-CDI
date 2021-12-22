<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"/>
    <h3>${ title }</h3>
    <%-- username.present, por detrás está llamando al username.isPresent() --%>
    <c:if test="${username.present}">
        <div class="alert alert-info">¡Hola ${username.get()}, bienvenido!</div>
        <a href="${pageContext.request.contextPath}/productos/form" class="btn btn-primary my-2">Crear  [+]</a>
    </c:if>

    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>nombre</th>
                <th>tipo</th>
                <c:if test="${username.present}">
                    <th>precio</th>
                    <th>agregar</th>
                    <th>editar</th>
                    <th>eliminar</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${productos}" var="p">
            <tr>
                <%-- p.id, por detrás está llamando al p.getId(), igual con los demás métodos p.getNombre(), p.getPrecio(), etc. --%>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.categoria.nombre}</td>
                <c:if test="${username.present}">
                    <td>${p.precio}</td>
                    <td><a href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}" class="btn btn-sm btn-primary">Agregar</a></td>
                    <td><a href="${pageContext.request.contextPath}/productos/form?id=${p.id}" class="btn btn-sm btn-success">Editar</a></td>
                    <td><a href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}" class="btn btn-sm btn-danger" onclick="javascript:return confirm('¿Está seguro que desea eliminar?');">Eliminar</a></td>
                </c:if>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <%-- Aquí le agregamos al atributo mensaje el contexto del cual viene, esto por que
    el atributo  mensaje se ha definido en varios contextos, entonces de esa forma se diferencia
    el valor del mensaje según el contexto. Si ubiera solo un atributo mensaje definido, entonces
    se podría obviar el contexto, es decir sin el applicationScope o requestScope --%>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp"/>