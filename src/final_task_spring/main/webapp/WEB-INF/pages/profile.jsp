<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Time Management</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>"/>
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet" />


         <link rel="stylesheet" href="<c:url value="/resources/fonts/icomoon/style.css"/>">

        <link rel="stylesheet" href="<c:url value="/resources/css/owl.carousel.min.css"/>">
    
        <!-- Style -->
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

    </head>
    <body id="page-top">
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>

        
        <!-- About Section-->
        <section class="page-section bg-primary text-white mb-0" id="about">
            <div class="container d-flex flex-column mt-5">
                <!-- About Section Heading-->
                <br>
                <h2 class="page-section-heading text-center text-uppercase text-white">
                    <span>
                        <spring:message code="users.profile.welcome"/>
                    </span>
                    <span>${user.firstName}</span>
                </h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Content-->
                <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <span>
                            <spring:message code="users.profile.first_name"/>
                        </span>
                <span>${user.firstName}</span>
                    </div>
                    <div class="col-lg-4 ms-auto">
                        <span>
                            <spring:message code="users.profile.username"/>
                        </span>
                <span>${user.username}</span>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <span>
                            <spring:message code="users.profile.last_name"/>
                        </span>
                <span>${user.lastName}</span>
                    </div>
                    <div class="col-lg-4 ms-auto"><span>
                        <spring:message code="users.update.age.label"/>
                    </span>
                <span>${user.age}</span></div>
                </div>
                <br>
              <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <span>
                            <spring:message code="users.update.gender.label"/>
                        </span>
                <span>${user.gender}</span>
                    </div>
                    <div class="col-lg-4 ms-auto"><span>
                        <spring:message code="users.update.contact.label"/>
                    </span>
                <span>${user.contact}</span></div>
                </div>

                
                <!-- About Section Button-->
                <div class="text-center mt-4">
                    <a class="btn btn-xl btn-outline-light" href="${pageContext.request.contextPath}/userProfileUpdate">
                        <img src="<c:url value="/resources/images/pencil.png"/>" width="35px">
                        <spring:message code="users.profile.edit_button"/>
                    </a>
                </div>
                <br>
                <br>
                <div class="table-responsive">
        <c:if test="${user.activities.isEmpty()}">
                    <span>
                        <spring:message code="users.profile.activities.empty"/>
                    </span>
                </c:if>
                <c:if test="${!user.activities.isEmpty()}">
                    <h2 class="display-5">
                        <spring:message code="link.activities"/>
                    </h2>
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col">
                <spring:message code="users.profile.activities.name"/>
            </th>
              <th scope="col">
                <spring:message code="users.profile.activities.status"/>
            </th>

            </tr>
          </thead>
          <tbody>
            <c:forEach items="${user.activities}" var="activity">
            <tr scope="row">
                <td><a href="#">${activity.name}</a></td>
                <td>${activity.status}</td>          
            </tr>
             </c:forEach>
          </tbody>
        </table>
    </c:if>
      </div>

      <div class="table-responsive">
        <c:if test="${user.activityRequests.isEmpty()}">
                    <span>
                        <spring:message code="users.profile.activity.requests.empty"/>
                    </span>
                </c:if>
                <c:if test="${!user.activityRequests.isEmpty()}">
                    <h2 class="display-5">
                        <spring:message code="link.requests"/>
                    </h2>
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col">
                <spring:message code="users.profile.activity.requests.activity_name"/>
            </th>
              <th scope="col">
                <spring:message code="users.profile.activity.requests.action"/>
            </th>
              <th scope="col">
                <spring:message code="users.profile.activity.requests.status"/>
            </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${user.activityRequests}" var="activityRequest">
            <tr scope="row">
                <td><a href="#">${activityRequest.activity.name}</a></td>
                <td>${activityRequest.action}</td>      
                <td>${activityRequest.status}</td>      
            </tr>
             </c:forEach>
          </tbody>
        </table>
    </c:if>
      </div>
            </div>
        </section>
        
        <%@include file="/WEB-INF/fragments/footer.jspf" %>


        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="<c:url value="/resources/js/scripts.js"/>"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
