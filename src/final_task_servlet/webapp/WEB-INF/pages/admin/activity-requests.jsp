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
                <br>
                <h2 class="page-section-heading text-center text-uppercase text-white">Requests</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                
    <div class="card">
        <div class="card-header">
            <div>
                <c:if test="${requestScope.requests.isEmpty()}">
                    <h1 class="display-4"><fmt:message key="activity.request.empty"/></h1>
                    <hr>
                </c:if>
                <c:if test="${!requestScope.requests.isEmpty()}">
                    <h1 class="display-4"><fmt:message key="activity.request.title"/></h1>
                    <hr>
                    <%@include file="/WEB-INF/fragments/activity-requests-paginator.jspf" %>
                </c:if>
            </div>
        </div>
        <div class="card-body">
            <c:if test="${!requestScope.requests.isEmpty()}">
                <div class="row">
                    <c:forEach items="${requestScope.requests}" var="activityRequest">
                        <div class="col-md-3">
                            <div class="card mb-4 shadow-sm">
                                <div class="card-header">
                                    <p>${activityRequest.activity.name}</p>
                                    <p>${activityRequest.user.username}</p>
                                </div>
                                <div class="card-body">
                                    <p>
                                        <span><fmt:message key="activity.request.id"/></span>
                                        <span>${activityRequest.id}</span>
                                    </p>
                                    <p>
                                        <span><fmt:message key="activity.request.activity_id"/></span>
                                        <span>${activityRequest.activity.id}</span>
                                    </p>
                                    <p>
                                        <span><fmt:message key="activity.request.user_id"/></span>
                                        <span>${activityRequest.user.id}</span>
                                    </p>
                                    <p>
                                        <span><fmt:message key="activity.request.action"/></span>
                                        <span>${activityRequest.action.toString()}</span>
                                    </p>
                                    <p>
                                        <span><fmt:message key="activity.request.status"/></span>
                                        <span>${activityRequest.status.toString()}</span>
                                    </p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <a class="btn btn-sm btn-secondary"
                                               href="${pageContext.request.contextPath}/pages/admin/activityRequestApprove?id=${activityRequest.id}"
                                            >
                                                <fmt:message key="activity.request.button.approve"/>
                                            </a>
                                            <a class="btn btn-sm btn-secondary"
                                               href="${pageContext.request.contextPath}/pages/admin/activityRequestReject?id=${activityRequest.id}"
                                            >
                                                <fmt:message key="activity.request.button.reject"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
        <div class="card-footer">
            <%@include file="/WEB-INF/fragments/activity-requests-paginator.jspf" %>
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
