<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>



<div class="container mt-5">
    <form action="/recipe/search" method="GET">
        <div class="row justify-content-center">
            <div class="col-12 col-md-4 col-lg-3 mb-2" id="nav1">
                <label for="nameSearch" class="form-label">Recipe Name</label>
            </div>
            <div class="col-md-6 mb-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="nameSearch" name="nameSearch" placeholder="Search by recipe name" value="${nameSearch}">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>

<c:if test="${not empty recipeVar}">
    <section class="py-5" style="background-color: #f8f9fa;">
        <div class="container text-center">
            <h1 class="display-4 mb-5 font-weight-bold">Customers Found: ${recipe.size()}</h1>

            <div class="table-responsive">
                <table class="table table-hover" style="background-color: #ffffff;">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Image_URL</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${recipeVar}" var="recipe">
                            <tr>
                                <td>${recipe.id}</td>
                                <td>${recipe.name}</td>
                                <td>${recipe.description}</td>

                                <td><img src="${recipe.image_url}" style="max-width:100px"></td>
                                <td><a href="/recipe/edit/${recipe.id}">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </section>
</c:if>

 <jsp:include page="../include/footer.jsp"/>