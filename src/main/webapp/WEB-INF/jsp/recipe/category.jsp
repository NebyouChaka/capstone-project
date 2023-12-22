<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
<jsp:include page="../include/header.jsp"/>


    <section>
    <div class="container mt-5 mb-4">
        <h2>Recipes in Category: ${category}</h2>
    </section>

    <section>

    <table class="table">
        <thead>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${category}</td> <!-- Category column -->
                <c:forEach items="${recipes}" var="recipe">
                    <td>${recipe.name}</td>
                    <td>${recipe.description}</td>
                    <td><img src="${recipe.image_url}" style="height: 50px; width: auto;"></td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
</div>
<div></div>
<div></div>
<div></div>
<div></div>
<div></div>
<div></div>
</section>


<jsp:include page="../include/footer.jsp"/>
