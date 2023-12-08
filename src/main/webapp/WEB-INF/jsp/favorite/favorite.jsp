<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">


<div class="container mt-4">
    <h2>Favorite Recipes</h2>

    <c:if test="${not empty favoriteRecipes}">
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="recipe" items="${favoriteRecipes}">
                    <tr>
                        <td>${recipe.recipesName}</td>
                        <td>${recipe.type}</td>
                        <td><img src="${recipe.imagesURL}" alt="${recipe.recipesName}" class="img-thumbnail"></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty favoriteRecipes}">
        <p class="alert alert-info">No favorite recipes available.</p>
    </c:if>
</div>

<jsp:include page="../include/footer.jsp"/>