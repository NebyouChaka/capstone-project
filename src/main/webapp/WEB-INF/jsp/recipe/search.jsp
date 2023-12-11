<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>



<div class="container mt-5">
    <form action="/recipe/search" method="GET">
        <div class="row justify-content-center">
            <div class="col-12 col-md-4 col-lg-3 mb-2" id="nav1">
                <label for="nameSearch" class="form-label">Recipe Name</label>
            </div>
            <div class="col-md-6 mb-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="nameSearch" name="nameSearch" placeholder="Search by recipe name" value="${nameSearch}">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>


 <jsp:include page="../include/footer.jsp"/>