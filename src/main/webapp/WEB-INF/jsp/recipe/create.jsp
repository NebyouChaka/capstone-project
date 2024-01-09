<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>

 <section style="padding-top: 200px;">
    <div  >
        <div class="row">
            <div class="col-12 text-center">
                <h1 style="color: white;">Create Recipes</h1>
            </div>
        </div>
    </div>
</section>



<section ">
    <div class="container">
        <c:if test="${not empty success}">
            <div class="row justify-content-center">
                <div  text-center">
                    <div class="alert alert-success" role="alert">
                            ${success}
                    </div>
                </div>
            </div>
        </c:if>


        <form method="get" action="/recipe/createSubmit">
            <input type="hidden" name="id" value="${form.id}">

            <div >
                <label for="name" class="form-label" style="color: white;">Recipe Name</label>
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
              <div >
               <label for="description" class="form-label" style="color: white;">Description</label>
                <input type="text" class="form-control" id="description" name="description" aria-describedby="descriptionHelp" value="${form.description}">
                <div id="descriptionHelp" class="form-text">Please let us your recipe description</div>
                 </div>
                 <c:if test="${errors.hasFieldErrors('description')}">
                   <div style="color:red">
                      <c:forEach items="${errors.getFieldErrors('description')}" var="error">${error.defaultMessage}<br>
                      </c:forEach>
                    </div>
                  </c:if>
                  <div >
                      <label for="category" class="form-label" style="color: white;">Category</label>
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

                  <div >
                      <label for="image_url" class="form-label" style="color: white;">Image URL</label>
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
<div class="spacer7"></div>

 <jsp:include page="../include/footer.jsp"/>