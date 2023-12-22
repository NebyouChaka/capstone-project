<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">

<jsp:include page="../include/header.jsp"/>

<section>
    <div class="bg-light2 pt-1 pb-1 mt-3 mb-3"> <!-- Added margin and padding classes -->
        <div class="row1">
            <div class="col-12 text-center">
                <h1 class="m-0">Recipe Details</h1>
            </div>
        </div>
    </div>
</section>


<section>
    <div class="content-flex-container">
        <div class="card-container2">
            <div class="card1">
                <div class="card-body1 p-3">
                    <h5 class="card-title">${recipe.name}</h5>
                    <p class="card-text"><strong>Recipe ID:</strong> ${recipe.id}</p>
                    <p class="card-text"><strong>Description:</strong> ${recipe.description}</p>
                    <p class="card-text"><strong>Category:</strong> ${recipe.category}</p>
                    <h3>Ingredients</h3>
                    <ul>
                        <c:forEach items="${ingredients}" var="ingredient">
                            <li>${ingredient.ingredient.name} - Measurement: ${ingredient.measurement}, Quantity: ${ingredient.quantity}</li>
                        </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/recipe/addIngredient?recipeId=${recipe.id}" class="btn btn-primary">Add Ingredients</a>
                </div>
            </div>
        </div>

        <div class="image-container2 ">
            <div class="imageSetUp">
                <img class="card-img-top" src="${recipe.image_url}" alt="Card image cap" style="max-width: 300px; height: auto;">
            </div>
        </div>
    </div>
</section>
<jsp:include page="../include/footer.jsp"/>