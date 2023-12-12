<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../include/header.jsp"/>

<div class="container mt-5">
    <h1>My Favorite Recipes</h1>

    <c:if test="${empty userFavorites}">
        <p>You haven't added any recipes to your favorites yet.</p>
    </c:if>

    <c:if test="${not empty userFavorites}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Recipe Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userFavorites}" var="userFavorite">
                    <tr>
                        <td>${userFavorite.recipe.name}</td>
                        <td>${userFavorite.recipe.description}</td>
                        <td>
                            <a href="/recipe/detail/${userFavorite.recipe.id}" class="btn btn-primary">View Recipe</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<jsp:include page="../include/footer.jsp"/>
