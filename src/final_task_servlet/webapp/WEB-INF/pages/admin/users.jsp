<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tt" uri="time-tracker-tags" %>

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
        <link rel="icon" type="${pageContext.request.contextPath}/image/x-icon" href="${pageContext.request.contextPath}/WEB-INF/pages/assets/favicon.ico" />
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
                <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <c:if test="${!requestScope.users.isEmpty()}">
                        <h1 class="display-4"><fmt:message key="users.header"/></h1>
                        <hr>
                    </c:if>
                    <c:if test="${requestScope.users.isEmpty()}">
                        <h1 class="display-4"><fmt:message key="users.empty"/></h1>
                        <hr>
                    </c:if>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                <fmt:message key="users.id"/>
                            </th>
                            <th>
                                <fmt:message key="users.first_name"/>
                            </th>
                            <th>
                                <fmt:message key="users.last_name"/>
                            </th>
                            <th>
                                <fmt:message key="users.username"/>
                            </th>
                            <th>
                                <fmt:message key="users.password"/>
                            </th>
                            <th>
                                <fmt:message key="users.roles"/>
                            </th>
                            <th>
                                <fmt:message key="users.edit"/>
                            </th>
                            <th>
                                <fmt:message key="users.delete"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.username}</td>
                                <td>${user.password}</td>
                                <td>${user.authorities}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/pages/admin/userUpdate?id=${user.id}"
                                       class="btn btn-primary">
                                        <img src="${pageContext.request.contextPath}/images/outline-edit.png"
                                             alt="edit user">
                                    </a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/pages/admin/userDelete?id=${user.id}"
                                       class="btn btn-primary">
                                        <img src="${pageContext.request.contextPath}/images/outline-delete.png"
                                             alt="delete user">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer">
                    <%@include file="/WEB-INF/fragments/users-paginator.jspf" %>
                </div>
            </div>
        </div>
    </div>
                
            </div>
        </section>
        
<%@include file="/WEB-INF/fragments/footer.jspf" %>
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}/WEB-INF/pages/js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
