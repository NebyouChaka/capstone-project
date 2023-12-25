<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="include/header.jsp"/>




<div class="scrollable-content"> <!-- Add this div for scrollable content -->
     <div class="text-center mt-5">
         <h2>Featured Recipes</h2>
     </div>

    <!-- Inserted images start here -->
<div class="image-container" style="background-color: white; padding-left: 100px;">
    <div class="col-md-3 mb-4" style= "padding-left: 130px;">
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



<div class="container-fluid mt-5">
    <h1>Recipes</h1>
    <div class="row">
        <c:forEach items="${recipes}" var="recipe">
            <div class="col-md-3 mb-4">
                <div class="recipe-item">
                    <a href="${pageContext.request.contextPath}/recipe/detail?id=${recipe.id}">
                        <div class="image-container">
                            <img src="${recipe.image_url}" alt="Image of ${recipe.name}" class="img-fluid">
                        </div>
                        <h4>${recipe.name}</h4>
                    </a>
                    <form action="${pageContext.request.contextPath}/addFavorite" method="post">
                        <input type="hidden" name="recipeId" value="${recipe.id}">
                        <button type="submit">Add to Favorites</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="spacer"></div>


    <script src="${pageContext.request.contextPath}/js/myScript.js"></script>


<jsp:include page="include/footer.jsp"/>

