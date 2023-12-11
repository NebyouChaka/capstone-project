<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<div class="container mt-4">
    <h2>Featured Recipes</h2>

    <!-- Inserted images start here -->
    <div class="row">
        <div class="col-md-3 mb-4">
            <img src="/pub/images/image1.png" alt="Image 1" class="img-fluid" style="height: 200px;">
        </div>
        <div class="col-md-3 mb-4">
            <img src="/pub/images/imag2.png" alt="Image 2" class="img-fluid" style="height: 200px;">
        </div>
        <div class="col-md-3 mb-4">
            <img src="/pub/images/image3.png" alt="Image 3" class="img-fluid" style="height: 200px;">
        </div>
        <div class="col-md-3 mb-4">
            <img src="/pub/images/image4.png" alt="Image 4" class="img-fluid" style="height: 200px;">
        </div>
    </div>
    <!-- Inserted images end here -->

    <c:if test="${not empty featuredRecipes}">
        <div class="row">
            <c:forEach var="recipe" items="${featuredRecipes}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="${recipe.imagesURL}" alt="${recipe.recipesName}" class="card-img-top img-fluid" style="height: 200px;">
                        <div class="card-body">
                            <h5 class="card-title">${recipe.name}</h5>
                            <p class="card-text">${recipe.id}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty featuredRecipes}">
        <p class="alert alert-info">No featured recipes available.</p>
    </c:if>
</div>

<jsp:include page="include/footer.jsp"/>
