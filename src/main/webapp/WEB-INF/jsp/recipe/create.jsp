<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>

 <section style="padding-top: 200px;">
    <div class="bg-light2 pt-5 pb-5" >
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
                <label for="name" class="form-label">Recipe Name</label>
                <input type="text" class="form-control" id="name" name="name" aria-describedby="recipeNameHelp" value="${form.name}">
                <div id="recipeNameHelp" class="form-text">Please let us know your recipe name</div>
            </div>
            <c:if test="${errors.hasFieldErrors('name')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('name')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>
              <div class="mt-3">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control" id="description" name="description" aria-describedby="descriptionHelp" value="${form.description}">
                            <div id="descriptionHelp" class="form-text">Please let us your recipe description</div>
                        </div>
                        <c:if test="${errors.hasFieldErrors('description')}">
                            <div style="color:red">
                                <c:forEach items="${errors.getFieldErrors('description')}" var="error">
                                    ${error.defaultMessage}<br>
                                </c:forEach>
                            </div>
                        </c:if>
                  <div class="mt-3">
                      <label for="category" class="form-label">Category</label>
                      <input type="text" class="form-control" id="category" name="category" aria-describedby="categoryHelp" value="${form.category}">
                      <div id="categoryHelp" class="form-text">Please specify the recipe category (e.g., Breakfast, Lunch, Dinner).</div>
                  </div>
                  <c:if test="${errors.hasFieldErrors('category')}">
                      <div style="color: red;">
                          <c:forEach items="${errors.getFieldErrors('category')}" var="error">
                              ${error.defaultMessage}<br>
                          </c:forEach>
                      </div>
                  </c:if>

                  <div class="mt-3">
                      <label for="image_url" class="form-label">Image URL</label>
                         <input type="text" class="form-control" id="image_url" name="image_url" value="${form.image_url}">
                         </div>
                      <c:if test="${errors.hasFieldErrors('image_url')}">
                           <div style="color:red">
                                 <c:forEach items="${errors.getFieldErrors('image_url')}" var="error">
                                        ${error.defaultMessage}<br>
                                   </c:forEach>
                             </div>
                       </c:if>

            <button type="submit" class="btn btn-primary mt-4">Submit</button>
        </form>
    </div>
</section>
<div class="spacer"></div>

 <jsp:include page="../include/footer.jsp"/>