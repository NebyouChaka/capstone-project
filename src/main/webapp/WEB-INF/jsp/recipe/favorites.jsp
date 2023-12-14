<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../include/header.jsp"/>

 <div class="container">
        <h2>${user.name}'s Favorite Recipes</h2>

        <c:choose>
            <c:when test="${empty favoriteRecipes}">
                <p>You have no favorite recipes.</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${favoriteRecipes}" var="recipe">
                        <li>
                            <h3>${recipe.name}</h3>
                            <p>Description: ${recipe.description}</p>
                            <!-- Add more recipe details as needed -->

                            <!-- Update Button/Form -->
                            <form action="${pageContext.request.contextPath}/recipe/updateFavorite" method="post">
                                <input type="hidden" name="favoriteId" value="${recipe.id}"/>
                                <input type="submit" value="Update"/>
                            </form>

                            <!-- Delete Button/Form -->
                            <form action="${pageContext.request.contextPath}/recipe/deleteFavorite" method="post">
                                <input type="hidden" name="favoriteId" value="${recipe.id}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

<jsp:include page="../include/footer.jsp"/>
