<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<%@ include file="../include/header.jsp" >

<div class="container mt-5" id="background1">
    <h1>Recipe Details</h1>

    <div class="row">
        <div class="col-md-4">
            <img src="${recipe.image_url}" alt="${recipe.name}" style="width: 600px; height: 200px;">
        </div>
        <div class="col-md-8">
            <h2>${recipe.name}</h2>
            <p><strong>Description:</strong> ${recipe.description}</p>
            <!-- Add more recipe details here as needed -->
        </div>
    </div>

    <a href="/recipes" class="btn btn-primary mt-3">Back to Recipes</a>
</div>
<div class="spacer3"></div>
<%@ include file="../include/footer.jsp" >
