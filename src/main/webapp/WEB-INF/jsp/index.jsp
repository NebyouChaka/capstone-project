<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="./include/header.jsp"/>
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
<div class="container mt-5">
    <h1>Recipes</h1>

    <div class="recipes-container1">
       <c:forEach items="${recipes}" var="recipe">
           <div class="recipe-item">
               <a href="${pageContext.request.contextPath}/recipe/detail?id=${recipe.id}">
                   <img src="${recipe.image_url}" alt="Image of ${recipe.name}" class="img-fluid" style="height: 200px;">
                   <h3>${recipe.name}</h3>
               </a>
           </div>
       </c:forEach>
    </div>
</div>
<div class="spacer"></div>


