<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
          <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>
 <div class="main-content">
<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-custom fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand " href="/">The Recipes</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
       <div class="mx-auto"  id="navbarNav">
           <ul class="navbar-nav justify-content-center">
          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                   Recipes
                   </a>
                         <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                             <li><a class="dropdown-item" href="/recipe/category/Breakfast">Breakfast</a></li>
                             <li><a class="dropdown-item" href="/recipe/category/Lunch">Lunch</a></li>
                             <li><a class="dropdown-item" href="/recipe/category/Dinner">Dinner</a></li>
                             <li><a class="dropdown-item" href="/recipe/category/All">All Recipes</a></li>
                         </ul>
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

                         <img src="@{/pub/images/{filename}(filename=${user.profilePhoto})}" alt="Profile Photo" class="profile-photo">


                     </li>
                     <li class="nav-item">
                          <a class="nav-link" href="/favorites">My Favorites</a>
                      </li>
                   <li class="nav-item">
                       <a class="nav-link" href=""><sec:authentication property="principal.username"/></a>
                   </li>
               </sec:authorize>
               <li class="nav-item">
                   <a class="nav-link" href="/admin/index">Secured Request</a>
               </li>
           </ul>
       </div>

    </div>
</nav>
  </div>