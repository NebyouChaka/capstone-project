<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<!-- addIngredient.html or addIngredient.jsp -->
<form action="/recipe/addIngredientSubmit" method="post">
    <input type="hidden" name="recipeId" value="${recipeId}" />

    <c:forEach items="${ingredients}" var="ingredient">
        <input type="checkbox" id="ingredient${ingredient.id}" name="ingredientIds" value="${ingredient.id}">
        <label for="ingredient${ingredient.id}">${ingredient.name}</label><br>
    </c:forEach>

    <label for="measurement">Measurement:</label>
    <input type="text" id="measurement" name="measurement" />

    <button type="submit">Add Ingredients</button>
</form>



 <jsp:include page="../include/footer.jsp"/>