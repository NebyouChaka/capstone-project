<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<div class="container mt-4">
    <h2>All Recipes</h2>

    <c:if test="${not empty allRecipes}">
        <div class="row">
            <c:forEach var="recipe" items="${allRecipes}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="${recipe.imagesURL}" alt="${recipe.recipesName}" class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${recipe.recipesName}</h5>
                            <p class="card-text">${recipe.type}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty allRecipes}">
        <p class="alert alert-info">No recipes available.</p>
    </c:if>
</div>

<jsp:include page="../include/footer.jsp"/>
