<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="timeManage" uri="time-management" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <span class="text-danger align-content-center">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        ${error}<br>
                                    </c:forEach>
                                </span>
                            </div>
                        </div>
        <div class="table-responsive">
        <c:if test="${users.isEmpty()}">
                        <h1 class="display-4">
                            <spring:message code="users.empty"/>
                        </h1>
                    </c:if>
                <c:if test="${!requestScope.users.isEmpty()}">

              
        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col"><spring:message code="users.id"/></th>
              <th scope="col"><spring:message code="users.first_name"/></th>
              <th scope="col"><spring:message code="users.last_name"/></th>
              <th scope="col"><spring:message code="users.username"/></th>
              <th scope="col"><spring:message code="users.age"/></th>
              <th scope="col"><spring:message code="users.gender"/></th>
              <th scope="col"><spring:message code="users.contact"/></th>
                <th scope="col"><spring:message code="users.admin.label"/></th>
              <th scope="col"><spring:message code="users.delete"/></th>
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
                <c:if test="${!user.getRoles().contains(adminRole)}">
                        <td>
                        <a href="${pageContext.request.contextPath}/admin/setAdmin?user_id=${user.id}" class="btn btn-primary bg-dark text-white">
                            <spring:message code="users.set_admin"/>
                        </a>
                        </td> 
                </c:if>
                <c:if test="${user.getRoles().contains(adminRole)}">
                    <td>
                    
                        <a href="${pageContext.request.contextPath}/admin/deleteAdmin?user_id=${user.id}" class="btn btn-primary bg-dark text-white">
                            <spring:message code="users.delete_admin"/>
                        </a>
                    
                    </td>
                </c:if> 
                <td>
                    <a href="${pageContext.request.contextPath}/admin/userDelete?id=${user.id}" class="btn btn-primary bg-dark text-white">
                        <spring:message code="users.delete"/>
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
