<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">


 <section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Create Recipes</h1>
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
        <form method="get" action="/recipe/createSubmit">
            <input type="hidden" name="id" value="${form.id}">

            <div class="mt-3">
                <label for="recipeName" class="form-label">Recipe Name</label>
                <input type="text" class="form-control" id="recipeName" name="recipeName" aria-describedby="recipeNameHelp" value="${form.firstName}">
                <div id="recipeNameHelp" class="form-text">Please let us know your recipe name</div>
            </div>
            <c:if test="${errors.hasFieldErrors('recipeName')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('recipeName')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>
              <div class="mt-3">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control" id="description" name="description" aria-describedby="descriptionHelp" value="${form.firstName}">
                            <div id="descriptionHelp" class="form-text">Please let us your recipe description</div>
                        </div>
                        <c:if test="${errors.hasFieldErrors('recipeName')}">
                            <div style="color:red">
                                <c:forEach items="${errors.getFieldErrors('recipeName')}" var="error">
                                    ${error.defaultMessage}<br>
                                </c:forEach>
                            </div>
                        </c:if>
                  <div class="mt-3">
                      <label for="imageUrl" class="form-label">Image URL</label>
                         <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${form.imageUrl}">
                         </div>
                      <c:if test="${errors.hasFieldErrors('imageUrl')}">
                           <div style="color:red">
                                 <c:forEach items="${errors.getFieldErrors('imageUrl')}" var="error">
                                        ${error.defaultMessage}<br>
                                   </c:forEach>
                             </div>
                       </c:if>

            <button type="submit" class="btn btn-primary mt-4">Submit</button>
        </form>
    </div>
</section>


 <jsp:include page="../include/footer.jsp"/>