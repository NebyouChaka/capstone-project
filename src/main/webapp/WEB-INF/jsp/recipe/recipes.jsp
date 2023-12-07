<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">



<div class="container mt-5">
    <h2>Recipe List</h2>

    <!-- Check if recipes are available -->
    <c:if test="${not empty recipes}">
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Recipe Name</th>
                    <th>Type</th>
                    <th>Images</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate over recipes and display each row -->
                <c:forEach var="recipe" items="${recipes}">
                    <tr>
                        <td>${recipe.id}</td>
                        <td>${recipe.recipesName}</td>
                        <td>${recipe.type}</td>
                        <td>${recipe.images}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Display a message if no recipes are available -->
    <c:if test="${empty recipes}">
        <p>No recipes available.</p>
    </c:if>
</div>


 <jsp:include page="../include/footer.jsp"/>