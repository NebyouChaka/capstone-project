<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css" />

<div class="container">
    <h1>Ingredients List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ingredient" items="${ingredients}">
                <tr>
                    <td>${ingredient.id}</td>
                    <td>${ingredient.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../include/footer.jsp" />
