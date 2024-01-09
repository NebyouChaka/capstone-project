<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>



<section class="high" >
<div class=" text-center">
           <h1 style="color: white;">User Registration</h1>
     </div>
      <div >
             <c:if test="${not empty success}">
                 <div class="row justify-content-center">
                     <div class="text-center">
                         <div class="alert alert-success" role="alert">
                                 ${success}
                         </div>
                     </div>
                 </div>
             </c:if>
    <div class="container5">
        <div class="row justify-content-center">
            <div class="col-2">
                <form method="post" action="/auth/registerSubmit" enctype="multipart/form-data" class="custom-card">
                    <div class="mt-3">
                        <label for="email" class="form-label" style="color: white;">Email</label>
                        <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="${form.email}">
                    </div>
                    <c:if test="${errors.hasFieldErrors('email')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('email')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="mt-3">
                        <label for="password" class="form-label" style="color: white;">Password</label>
                        <input type="password" class="form-control" id="password" name="password" value="${form.password}">
                    </div>
                    <c:if test="${errors.hasFieldErrors('password')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('password')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="mt-3">
                        <label for="confirmPassword" class="form-label" style="color: white;">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="${form.confirmPassword}">
                    </div>
                    <c:if test="${errors.hasFieldErrors('confirmPassword')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('confirmPassword')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="mt-3">
                        <label for="profilePhoto" class="form-label" style="color: white;">Profile Photo</label>
                        <input type="file" class="form-control-file" id="profilePhoto" name="profilePhoto">
                    </div>

                    <<div class="container">
                       <div class="d-flex justify-content-center">
                         <button type="submit" class="btn btn-primary custom-button">Submit</button>
                       </div>
                     </div>
                </form>
            </div>
        </div>
    </div>
</section>

<div class="spacer5"></div>
<jsp:include page="../include/footer.jsp"/>
