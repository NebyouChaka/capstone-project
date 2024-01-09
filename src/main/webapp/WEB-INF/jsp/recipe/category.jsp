<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>


<section>
    <div class="container ">
        <h2 style="padding: 100px;">Recipes in Category: ${category}</h2>
    </div>
</section>

<div class="content-wrapper">
    <section>
         <table class="table">
             <thead>
                      <tr>
                        <th style="color: white;">Category</th>
                        <th style="color: white;">Name</th>
                        <th style="color: white;">Description</th>
                        <th style="color: white;">Image</th>
                        <th style="color: white;">Details</th>
                      </tr>
            </thead>
            <tbody>
                <c:forEach items="${recipes}" var="recipe">
                      <tr>
                        <td style="color: white;">${category}</td> <!-- Category column -->
                        <td style="color: white;">${recipe.name}</td>
                        <td style="color: white;">${recipe.description}</td>
                        <td style="color: white;"><img src="${recipe.image_url}" alt="Image of ${recipe.name}" style="height: 100px; width: 200px;"></td>
                         <td style="color: white;">
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
