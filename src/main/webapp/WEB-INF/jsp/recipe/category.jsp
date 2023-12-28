<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>


<section>
    <div class="container mt-5 mb-4">
        <h2>Recipes in Category: ${category}</h2>
    </div>
</section>

<div class="content-wrapper">
    <section>
         <table class="table">
             <thead>
                      <tr>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Details</th>
                      </tr>
            </thead>
            <tbody>
                <c:forEach items="${recipes}" var="recipe">
                      <tr>
                        <td>${category}</td> <!-- Category column -->
                        <td>${recipe.name}</td>
                        <td>${recipe.description}</td>
                        <td><img src="${recipe.image_url}" alt="Image of ${recipe.name}" style="height: 100px; width: 100px;"></td>
                         <td>
                         <a href="/recipe/detail?id=${recipe.id}" class="btn btn-primary">Details</a>
                         </td>
                    </tr>
                 </c:forEach>
            </tbody>
         </table>
    </div>
</div>


<div class="spacer3"></div>
<jsp:include page="../include/footer.jsp"/>
