<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<h2 class="text-center">Add Ingredients</h2>

<div class="d-flex flex-column align-items-center gap-3 mt-3 mb- 6">
    <form id="addIngredientForm" action="${pageContext.request.contextPath}/recipe/addIngredientSubmit" method="post" class="w-50">
        <input type="hidden" name="recipeId" value="${recipeId}"/>

        <div id="ingredientsContainer">
            <!-- Ingredient input fields will be added here by JavaScript -->
        </div>

        <button type="button" onclick="addIngredientInput()">Add More Ingredients</button>
        <button type="submit" class="btn btn-primary">Submit Ingredients</button>
    </form>
</div>

<div class="mt-4 text-center">
    <h3>Recipe Details</h3>
    <table class="table table-bordered w-75 mx-auto">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Ingredient Name</th>
                <th>Measurement</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${currentIngredients}" var="detail">
                <tr>
                    <td>${detail.recipe.name}</td>
                    <td>${detail.recipe.description}</td>
                    <td>${detail.ingredient.name}</td>
                    <td>${detail.measurement}</td>
                    <td>${detail.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/myScript.js"></script>

<jsp:include page="../include/footer.jsp"/>
