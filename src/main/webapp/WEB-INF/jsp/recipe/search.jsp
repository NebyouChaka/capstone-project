<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>


<section class="custom-container3">
<div class="custom-container2">
    <form action="/recipe/search" method="GET" class="search-form">
        <div class="form-row">

            <div class="form-column">
                <div class="input-group">
                    <input type="text" id="nameSearch" name="nameSearch" placeholder="Search by recipe name" value="${nameSearch}" required>
                    <button type="submit" class="search-button">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>
                <small class="error-message">
                    <c:if test="${not empty nameSearch && nameSearch.length() < 3}">
                        Name should be at least 3 characters long.
                    </c:if>
                </small>
            </div>
        </div>
    </form>
</div>
</section>

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
                                <td><img src="${recipe.image_url}" style="max-width:100px"></td>
                                <td><a href="/recipe/detail?id=${recipeItem.id}">Detail</a></td>
                                <td><a href="/recipe/edit/${recipeItem.id}">Edit</a></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</c:if>

<div class="spacer"></div>

<jsp:include page="../include/footer.jsp"/>
