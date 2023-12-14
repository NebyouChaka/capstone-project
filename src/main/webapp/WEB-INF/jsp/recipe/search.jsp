<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<div class="container mt-5 mb-4"> <!-- Added mb-4 to add margin at the bottom -->
    <form action="/recipe/search" method="GET">
        <div class="row justify-content-center">
            <div class="col-12 col-md col-lg-2 mb-2" id="nav1">
                <label for="nameSearch" class="form-label">Recipe Name</label>
            </div>
            <div class="col-md-6 mb-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="nameSearch" name="nameSearch" placeholder="Search by recipe name" value="${nameSearch}" required>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>
                <small class="text-danger">
                    <c:if test="${not empty nameSearch && nameSearch.length() < 3}">
                        Name should be at least 3 characters long.
                    </c:if>
                </small>
            </div>
        </div>
    </form>
</div>

<c:if test="${not empty recipeVar}">
    <section class="py-5" style="background-color: #f8f9fa;">
        <div class="container text-center">
            <h1 class="display-4 mb-5 font-weight-bold">Recipes Found: ${recipeVar.size()}</h1>

            <div class="table-responsive">
                <table class="table table-hover" style="background-color: #ffffff;">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                             <th>Category</th>
                            <th>Image_URL</th>
                            <th>Detail</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${recipeVar}" var="recipeItem">
                            <tr>
                                <td>${recipeItem.id}</td>
                                <td>${recipeItem.name}</td>
                                <td>${recipeItem.description}</td>
                                <td>${recipeItem.category}</td>
                                <td><img src="${recipeItem.image_url}" style="max-width:100px"></td>
                                <td><a href="/recipe/edit/${recipeItem.id}">Edit</a></td>
                                <td><a href="/recipe/detail?id=${recipeItem.id}">Detail</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</c:if>


<jsp:include page="../include/footer.jsp"/>
