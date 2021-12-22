<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="layout/header.jsp"/>
<h3>${ title }</h3>

<div class="row">
  <div class="col-12 col-md-6">
    <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="row my-2">
        <label for="username" class="form-label">Username</label>
        <div>
          <input type="text" name="username" id="username" class="form-control">
        </div>
      </div>
      <div class="row my-2" class="form-label">
        <label for="password">Password</label>
        <div>
          <input type="password" name="password" id="password" class="form-control">
        </div>
      </div>
      <div class="row my-2">
        <input type="submit" value="Login" class="btn btn-primary">
      </div>
    </form>
  </div>
</div>

<jsp:include page="layout/footer.jsp"/>