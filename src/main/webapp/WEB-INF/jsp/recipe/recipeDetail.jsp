<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="../include/header.jsp" %>

<div class="container mt-5">
    <h1>Recipe Details</h1>

    <!-- Display recipe details here -->
    <p><strong>Name:</strong> ${recipe.name}</p>
    <p><strong>Description:</strong> ${recipe.description}</p>
    <!-- Add more details as needed -->

    <c:if test="${userLoggedIn}">
        <form method="post" action="/recipe/favorite/${recipe.id}">
            <button type="submit" class="btn btn-primary">Add to Favorites</button>
        </form>
    </c:if>
</div>

<%@ include file="../include/footer.jsp" %>