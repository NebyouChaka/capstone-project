<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" >

<div class="container mt-5">
    <h1>Recipe Details</h1>

    <div class="row">
        <div class="col-md-4">
            <img src="${recipe.image_url}" alt="${recipe.name}" style="max-height: 200px;">
        </div>
        <div class="col-md-8">
            <h2>${recipe.name}</h2>
            <p><strong>Description:</strong> ${recipe.description}</p>
            <!-- Add more recipe details here as needed -->
        </div>
    </div>

    <a href="/recipes" class="btn btn-primary mt-3">Back to Recipes</a>
</div>

<%@ include file="../include/footer.jsp" >
