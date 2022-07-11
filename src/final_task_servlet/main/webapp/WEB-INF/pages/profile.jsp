<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="timeManage" uri="time-management" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Time Management</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />


         <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/icomoon/style.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    
        <!-- Style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    </head>
    <body id="page-top">
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>

        
        <!-- About Section-->
        <section class="page-section bg-primary text-white mb-0" id="about">
            <div class="container d-flex flex-column mt-5">
                <!-- About Section Heading-->
                <br>
                <h2 class="page-section-heading text-center text-uppercase text-white">
                    <span><fmt:message key="users.profile.welcome"/></span>
                    <span>${requestScope.user.firstName}</span>
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
                        <span><fmt:message key="users.profile.first_name"/></span>
                <span>${requestScope.user.firstName}</span>
                    </div>
                    <div class="col-lg-4 ms-auto">
                        <span><fmt:message key="users.profile.username"/></span>
                <span>${requestScope.user.username}</span>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <span><fmt:message key="users.profile.last_name"/></span>
                <span>${requestScope.user.lastName}</span>
                    </div>
                    <div class="col-lg-4 ms-auto"><span><fmt:message key="users.update.age.label"/></span>
                <span>${requestScope.user.age}</span></div>
                </div>
                <br>
              <div class="row">
                    <div class="col-lg-4 ms-auto">
                        <span><fmt:message key="users.update.gender.label"/></span>
                <span>${requestScope.user.gender}</span>
                    </div>
                    <div class="col-lg-4 ms-auto"><span><fmt:message key="users.update.contact.label"/></span>
                <span>${requestScope.user.contact}</span></div>
                </div>

                
                <!-- About Section Button-->
                <div class="text-center mt-4">
                    <a class="btn btn-xl btn-outline-light" href="${pageContext.request.contextPath}/pages/userProfileUpdate">
                        <img src="${pageContext.request.contextPath}/images/pencil.png" width="35px">
                        Edit profile
                    </a>
                </div>
                <br>
                <br>
                <div class="table-responsive">
        <c:if test="${requestScope.user.activities.isEmpty()}">
                    <span><fmt:message key="users.profile.activities.empty"/></span>
                </c:if>
                <c:if test="${!requestScope.user.activities.isEmpty()}">
                    <h2 class="display-5">
                    <fmt:message key="link.activities"/>
                    </h2>
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col"><fmt:message key="users.profile.activities.name"/></th>
              <th scope="col"><fmt:message key="users.profile.activities.status"/></th>

            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.user.activities}" var="activity">
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
        <c:if test="${requestScope.user.activityRequests.isEmpty()}">
                    <span><fmt:message key="users.profile.activity.requests.empty"/></span>
                </c:if>
                <c:if test="${!requestScope.user.activityRequests.isEmpty()}">
                    <h2 class="display-5">
                    <fmt:message key="link.requests"/>
                    </h2>
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col"><fmt:message key="users.profile.activity.requests.activity_name"/></th>
              <th scope="col"><fmt:message key="users.profile.activity.requests.action"/></th>
              <th scope="col"><fmt:message key="users.profile.activity.requests.status"/></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.user.activityRequests}" var="activityRequest">
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
        <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
