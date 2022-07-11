<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="timeManage" uri="WEB-INF/tags/implicit.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Time Management</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico" /> " />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link type="text/css" href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
    </head>

    <body id="page-top">
        <!-- Navigation-->
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>

        <!-- Masthead-->
        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
                <!-- Main Image-->
                <img class="masthead-avatar mb-5" src="<c:url value="/resources/assets/img/time-management.png" /> " alt="..." />
                <!-- Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">
                    <spring:message code="index.main.name"/>
                </h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Subheading -->
                <p class="masthead-subheading font-weight-light mb-0">
                    <spring:message code="index.main.desc"/>
                </p>
            </div>
        </header>
    <!-- Portfolio Section-->
    <c:if test="${pageContext.request.userPrincipal.authenticated}">
        <section class="page-section portfolio" id="portfolio">
            <div class="container">
                <!-- Portfolio Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">
                    <spring:message code="index.actions.label"/>
                </h2>
        
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Portfolio Grid Items-->
                <div class="row justify-content-center">
                    <c:if test="${pageContext.request.userPrincipal.authenticated}">
                        <!-- Portfolio Item 1-->
                        <div class="col-md-6 col-lg-4 mb-5">
                            <div class="portfolio-item">
                                <a href="${pageContext.request.contextPath}/profile" class="text-decoration-none">
                                    <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                        <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/sport-boy.png" />" height="140px" width="140px" alt="..." />
                                    <h1 class="display-6 text-center p-2 retroshd">
                                        <spring:message code="link.profile"/>
                                    </h1>
                                </a>
                            </div>
                        </div>
                    </c:if>
                <c:if test="${pageContext.request.userPrincipal.authenticated}">
                    <!-- Portfolio Item 2-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                        <a href="${pageContext.request.contextPath}/activities" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/activity.png" />" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.all_activities"/>
                            </h1>
                        </a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.authenticated}">
                    <!-- Portfolio Item 3-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/activities" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/badge.png"/>" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.mark_time"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </c:if>
                    <timeManage:authorityCheck auth="ADMIN" user_id="Me">
                    <!-- Portfolio Item 4-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/admin/users" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/customer.png"/> " alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.users"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </timeManage:authorityCheck>
                <timeManage:authorityCheck auth="ADMIN" user_id="Me">
                    <!-- Portfolio Item 5-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/admin/activitiesAdd" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/achievement.png"/>" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.add_activity"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </timeManage:authorityCheck>
                <timeManage:authorityCheck auth="ADMIN" user_id="Me">
                    <!-- Portfolio Item 6-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/admin/typesAdd" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="<c:url value="/resources/assets/img/portfolio/png/type.png"/>" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.type_add"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </timeManage:authorityCheck>
                 <timeManage:authorityCheck auth="ADMIN" user_id="Me">
                <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/admin/activitiesRequests" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid " src="<c:url value="/resources/assets/img/portfolio/png/award.png"/>" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <spring:message code="link.requests"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </timeManage:authorityCheck>
                </div>
            </div>
        </section>
    </c:if>
        

        <%@include file="/WEB-INF/fragments/footer.jspf" %>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  
        <script src="<c:url value="/resources/js/scripts.js"/>"></script>

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
