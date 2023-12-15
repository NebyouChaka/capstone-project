<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="../include/header.jsp" %>



<section>
    <div class="bg-light2  pt-1 pb-1">
        <div class="row1">
            <div class="col-12 text-center">
                <h1 class="m-0">Recipe Details</h1>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="content-flex-container"> <!-- Flexbox container -->

        <!-- Card Content -->
        <div class="card-container">
            <div class="card1">
                <div class="card-body1">
                    <h5 class="card-title">${recipe.name}</h5>
                    <p class="card-text"><strong>Recipe ID:</strong> ${recipe.id}</p>
                    <p class="card-text"><strong>Description:</strong> ${recipe.description}</p>
                    <p class="card-text"><strong>Category:</strong> ${recipe.category}</p>
                    <!-- Add more recipe details here as needed -->
                     <a href="/recipe/${recipe.id}/addIngredient" class="btn btn-secondary">Add Ingredient</a>
                </div>
            </div>
        </div>

        <!-- Image Container -->
        <div class="image-container">
            <div class="imageSetUp">
                <img class="card-img-top" src="${recipe.image_url}" alt="Card image cap" style="max-width: 400px; height: 400px;">
            </div>
        </div>

    </div>
</section>








<%@ include file="../include/footer.jsp" %>