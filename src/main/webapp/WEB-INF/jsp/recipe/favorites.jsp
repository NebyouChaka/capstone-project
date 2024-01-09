<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>

<div class="container7 ">
    <h1 class="mb-4" id="spacer1">Your Favorite Recipes</h1>
    <c:choose>
        <c:when test="${not empty favorites}">
            <div class="table-responsive">
                <table class="table table-hover" >
                    <thead class="thead-dark">
                        <tr>
                            <th style="color: white;">Id</th>
                            <th style="color: white;">Name</th>
                            <th style="color: white;">Description</th>
                            <th style="color: white;">Category</th>
                            <th style="color: white;">Image URL</th>
                            <th style="color: white;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${favorites}" var="favorite">
                            <tr>
                                <td style="color: white;">${favorite.recipe.id}</td>
                                <td style="color: white;">${favorite.recipe.name}</td>
                                <td style="color: white;">${favorite.recipe.description}</td>
                                <td style="color: white;">${favorite.recipe.category}</td>
                                <td style="color: white;"><img src="${favorite.recipe.image_url}" alt="${favorite.recipe.name}" style="max-width:100px; height:auto;"></td>
                                <td>
                                   <button class="button-link detail-button" onclick="window.location.href='/recipe/detail?id=${favorite.recipe.id}'">Detail</button>

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
