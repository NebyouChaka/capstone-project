<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">


   <div class="container7">
       <h2>Admin Dashboard: Recipes and Users Management</h2>
       <table class="table">
           <thead>
               <tr>
                   <th style="color: white;">Name</th>
                   <th style="color: white;">Description</th>
                   <th style="color: white;">Image</th>
                   <th style="color: white;">Actions</th>
               </tr>
           </thead>
           <tbody>
               <c:forEach items="${recipes}" var="recipe">
                   <tr>
                       <td style="color: white;">${recipe.name}</td>
                       <td style="color: white;">${recipe.description}</td>
                       <td><img src="${recipe.image_url}" style="height: 50px; width: auto;"></td>
                       <td>
                             <a href="${pageContext.request.contextPath}/recipe/edit/${recipe.id}" class="btn btn-custom-edit btn-sm">Edit</a>
                             <a href="${pageContext.request.contextPath}/recipe/delete/${recipe.id}" class="btn btn-custom-delete btn-sm" onclick="return confirm('Are you sure you want to delete this recipe?')">Delete</a>
                           <!-- Add Update button if necessary -->
                       </td>
                   </tr>
               </c:forEach>
           </tbody>
       </table>
   </div>
<div class="container7">
    <h2>Users</h2>
    <table class="table">
        <thead>
            <tr>
                <th style="color: white;">ID</th>
                <th style="color: white;">Email</th>
                <th style="color: white;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td style="color: white;">${user.id}</td>
                    <td style="color: white;">${user.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/delete/${user.id}" class="btn btn-custom-delete btn-sm" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                        <!-- Add Update button if necessary -->
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>




<jsp:include page="../include/footer.jsp"/>

