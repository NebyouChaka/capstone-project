<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pub/CSS/global-style.css">



<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Contact Us</h1>
            </div>
        </div>
    </div>
</section>

<section class="pt-5 pb-5">
     <form action="${pageContext.request.contextPath}/auth/contact/contactSubmit" id="contact_form" method="post">
     <spring:csrfInput />
                <div class="mb-3">
                  <label for="email">Email address</label>
                  <input type="email" required maxlength="50" class="form-control"
                  id="email" name="email" placeholder=" Insert your email">
                </div>
                <div class="mb-3">
                  <label for="name_input">Name</label>
                  <input type="text" required maxlength="50" class="form-control"
                  id="name_input" name="name" placeholder="Name">
                </div>
                <div class="mb-3">
                  <label for="message">Message</label>
                  <textarea class="form-control" id="message" name="message" rows="3"></textarea>
                </div>
              <div class="my-4">
              <div class="g-recaptcha" data-sitekey="6LchOyEUAAAAAPo9l0WmIgFkGu5D6Gyq-MjsIHS1"></div>
              </div>
                <button type="submit" class="btn btn-primary px-4">Send</button>
              </form>
</section>

 <jsp:include page="../include/footer.jsp"/>