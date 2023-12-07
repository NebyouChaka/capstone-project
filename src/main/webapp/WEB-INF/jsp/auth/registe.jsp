<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">




<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Create Customer</h1>
            </div>
        </div>
    </div>
</section>



<section class="pt-5 pb-5">
    <div class="container">
        <c:if test="${not empty success}">
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="alert alert-success" role="alert">
                            ${success}
                    </div>
                </div>
            </div>
        </c:if>

        <!-- the action attribute on the form tag is the URL that the form will submit to when then user clicks the submit button -->
        <form method="get" action="/customer/createSubmit">
            <input type="hidden" name="id" value="${form.id}">

            <div class="mt-3">
                <label for="recipeName" class="form-label">Recipe Name</label>
                <input type="text" class="form-control" id="recipeName" name="recipeName" aria-describedby="frecipeNamHelp" value="${form.recipeName}">
                <div id="recipeNamHelp" class="form-text">Please let us know your first name</div>
            </div>
            <c:if test="${errors.hasFieldErrors('recipeName')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('recipeName')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>


            <div class="mt-3">
                <label for="type" class="form-label">Type of food</label>
                <input type="text" class="form-control" id="type" name="type" value="${form.type}">
            </div>
            <c:if test="${errors.hasFieldErrors('type')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('type')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>

            <div class="mt-3">
                <label for="imageURL" class="form-label">Upload Image</label>
                <input type="text" class="form-control" id="imageURL" name="imageURL" value="${form.imageURL}">
            </div>
            <c:if test="${errors.hasFieldErrors('imageURL')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('imageURL')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>


            <button type="submit" class="btn btn-primary mt-4">Submit</button>
        </form>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>