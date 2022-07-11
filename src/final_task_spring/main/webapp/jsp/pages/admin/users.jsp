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
        <title>Time Management System</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>" />
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

        <section class="page-section bg-primary text-white mb-0" id="about">
            <div class="container d-flex flex-column mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-xl-7">
                <!--<span class="text-danger align-content-center">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        ${error}<br>
                                    </c:forEach>
                                </span>-->
                            </div>
                        </div>
        <div class="table-responsive">
        <c:if test="${users.isEmpty()}">
                        <h1 class="display-4"><fmt:message key="users.empty"/></h1>
                    </c:if>
                <c:if test="${!requestScope.users.isEmpty()}">

              
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col"><fmt:message key="users.id"/></th>
              <th scope="col"><fmt:message key="users.first_name"/></th>
              <th scope="col"><fmt:message key="users.last_name"/></th>
              <th scope="col"><fmt:message key="users.username"/></th>
              <th scope="col"><fmt:message key="users.age"/></th>
              <th scope="col"><fmt:message key="users.gender"/></th>
              <th scope="col"><fmt:message key="users.contact"/></th>
              <th scope="col"><fmt:message key="users.edit"/></th>
              <th scope="col"><fmt:message key="users.delete"/></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${users}" var="user">
            <tr scope="row">
                <td><a href="#">${user.id}</a></td>
                <td>${user.firstName}</td>      
                <td>${user.lastName}</td>  
                <td>${user.username}</td>
                <td>${user.age}</td>
                <td>${user.gender}</td>
                <td>${user.contact}</td>   
                <td>
                    <a href="${pageContext.request.contextPath}/admin/userUpdate?id=${user.id}" class="btn btn-primary bg-dark text-white">
                        Edit user
                    </a>
                </td> 
                <td>
                    <a href="${pageContext.request.contextPath}/admin/userDelete?id=${user.id}" class="btn btn-primary bg-dark text-white">
                        Delete user
                    </a>
                </td>
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
