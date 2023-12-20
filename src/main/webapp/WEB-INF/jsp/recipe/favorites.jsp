<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<div class="container mt-5 mb-4">
    <h1 class="mb-4">Your Favorite Recipes</h1>
    <c:choose>
        <c:when test="${not empty favorites}">
            <div class="table-responsive">
                <table class="table table-hover" style="background-color: #ffffff;">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Image URL</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${favorites}" var="favorite">
                            <tr>
                                <td>${favorite.recipe.id}</td>
                                <td>${favorite.recipe.name}</td>
                                <td>${favorite.recipe.description}</td>
                                <td>${favorite.recipe.category}</td>
                                <td><img src="${favorite.recipe.image_url}" alt="${favorite.recipe.name}" style="max-width:100px; height:auto;"></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/recipe/details/${favorite.recipe.id}" class="btn btn-info btn-sm btn-action" role="button">Details</a>
                                    <a href="/removeFavorite?recipeId=${favorite.recipe.id}" class="btn btn-danger btn-sm btn-action" role="button" onclick="return confirm('Are you sure you want to remove this recipe from favorites?')">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">You have no favorite recipes.</div>
        </c:otherwise>
    </c:choose>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/myScript.js"></script>
<jsp:include page="../include/footer.jsp"/>
