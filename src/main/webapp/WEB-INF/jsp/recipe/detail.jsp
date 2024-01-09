<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">

<jsp:include page="../include/header.jsp"/>

<section>
    <div > <!-- Added margin and padding classes -->
        <div class="row1">
            <div class="col-12 text-center">
                <h1 class="m-0" id="spacer2">Recipe Details</h1>
            </div>
        </div>
    </div>

</section>


<section>
    <div class="content-flex-container">
        <div class="card-container2">
            <div class="card1">

                    <p class="card-text"><strong>Recipe Name: </strong><strong>${recipe.name}</strong></p>
                    <p class="card-text"><strong>Recipe ID:</strong> ${recipe.id}</p>
                    <p class="card-text"><strong>Description:</strong> ${recipe.description}</p>
                    <p class="card-text"><strong>Category:</strong> ${recipe.category}</p>
                    <h3><strong>Ingredients</strong></h3>
                    <ul>
                        <c:forEach items="${ingredients}" var="ingredient">
                            <li>${ingredient.ingredient.name} - Measurement: ${ingredient.measurement}, Quantity: ${ingredient.quantity}</li>
                        </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/recipe/addIngredient?recipeId=${recipe.id}" class="btn btn-primary">Add Ingredients</a>


            </div>
        </div>

        <div class="image-container2 ">
            <div class="imageSetUp">
                <img class="card-img-top" src="${recipe.image_url}" alt="Card image cap" >
            </div>
        </div>
    </div>
</section>
<div class="spacer3"></div>
<script src="${pageContext.request.contextPath}/js/myScript.js"></script>
<jsp:include page="../include/footer.jsp"/>