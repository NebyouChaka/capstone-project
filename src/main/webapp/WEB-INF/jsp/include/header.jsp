<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
          <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
          <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-custom">
    <div class="container-fluid">
        <a class="navbar-brand " href="/">The Recipes</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
       <div class="mx-auto"  id="navbarNav">
           <ul class="navbar-nav justify-content-center">
          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Recipes
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/recipe/recipes">All Recipes</a>
                  <c:forEach items="${categories}" var="category">
                      <a class="dropdown-item" href="${pageContext.request.contextPath}/recipe/recipes/${category.toLowerCase()}">${category}</a>
                  </c:forEach>
              </div>
          </li>
               <li class="nav-item">
                   <a class="nav-link" href="/recipe/create">Create recipes</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link" href="/recipe/search">Search recipes</a>
                </li>

               <sec:authorize access="!isAuthenticated()">
                  <li class="nav-item">
                        <a class="nav-link" href="/auth/register">User Registration</a>
                    </li>
                   <li class="nav-item">
                       <a class="nav-link" href="/auth/login">Login</a>
                   </li>
               </sec:authorize>

               <sec:authorize access="hasAnyAuthority('ADMIN')">
                   <li class="nav-item">
                       <a class="nav-link" href="/admin/index">Admin</a>
                   </li>
               </sec:authorize>
               <sec:authorize access="isAuthenticated()">
                   <li class="nav-item">
                       <a class="nav-link" href="/auth/logout">Logout</a>
                   </li>
                   <li class="nav-item">
                       <a class="nav-link" href=""><sec:authentication property="principal.username" /></a>
                   </li>
               </sec:authorize>
               <li class="nav-item">
                   <a class="nav-link" href="/admin/index">Secured Request</a>
               </li>
           </ul>
       </div>

    </div>
</nav>
</body>
</html>