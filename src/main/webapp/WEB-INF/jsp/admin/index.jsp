<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/css/global-style.css">

<body>
    <div class="container">
        <header class="text-center mt-5">
            <h1>Recipe Management - Admin</h1>
        </header>

        <section class="mt-5">
            <h2>Create New Recipe</h2>
            <form action="createRecipe" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="recipeName">Recipe Name:</label>
                    <input type="text" class="form-control" id="recipeName" name="recipeName" required>
                </div>

                <!-- Add other form fields here (category, description, ingredients, instructions, image) -->

                <button type="submit" class="btn btn-primary">Create Recipe</button>
            </form>
        </section>

        <section class="mt-5">
            <h2>Manage Recipes</h2>
            <!-- List of existing recipes with edit and delete options -->
            <table class="table">
                <thead>
                    <tr>
                        <th>Recipe Name</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Spaghetti Carbonara</td>
                        <td>Italian</td>
                        <td>
                            <a href="editRecipe?id=1" class="btn btn-primary">Edit</a>
                            <a href="deleteRecipe?id=1" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                    <!-- Add more recipe rows as needed -->
                </tbody>
            </table>
        </section>

        <!-- Include footer or additional sections as required -->
    </div>
</body>
</html>

<jsp:include page="../include/footer.jsp"/>

