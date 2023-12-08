<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<div class="container mt-4">
    <h2>Select Your Favorite Recipes</h2>

    <form:form method="post" action="/favorite/save" modelAttribute="favorite">
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Select</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="recipe" items="${recipes}">
                    <tr>
                        <td>${recipe.recipesName}</td>
                        <td>${recipe.type}</td>
                        <td>
                            <form:checkbox path="recipeIds" value="${recipe.id}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <button type="submit" class="btn btn-primary">Save Favorites</button>
    </form:form>

    <c:if test="${empty recipes}">
        <p class="alert alert-info">No recipes available.</p>
    </c:if>
</div>

<jsp:include page="../include/footer.jsp"/>
