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
        <title>Time Management</title>
        <!-- Favicon-->
        <link rel="icon" type="${pageContext.request.contextPath}/image/x-icon" href="${pageContext.request.contextPath}/WEB-INF/pages/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    </head>



    <body id="page-top">
        <!-- Navigation-->
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>

        <!-- Masthead-->
        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
                <!-- Masthead Avatar Image-->
                <img class="masthead-avatar mb-5" src="${pageContext.request.contextPath}/images/Pngtree—timer icon or management time_5194069.png" alt="..." />
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">
                    <fmt:message key="index.main.name"/>
                </h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                <p class="masthead-subheading font-weight-light mb-0">
                    <fmt:message key="index.main.desc"/>
                </p>
            </div>
        </header>
        <!-- Portfolio Section-->
        <section class="page-section portfolio" id="portfolio">
            <div class="container">
                <!-- Portfolio Section Heading-->
                <c:if test="${sessionScope.authUser != null}">
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">
                    <fmt:message key="index.actions.label"/>
                </h2>
            </c:if>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Portfolio Grid Items-->
                <div class="row justify-content-center">
                    <c:if test="${sessionScope.authUser != null}">
                    <!-- Portfolio Item 1-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/profile" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/avatar.png" width="100px" heigth="100px" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.profile"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.authUser != null}">
                    <!-- Portfolio Item 2-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                        <a href="${pageContext.request.contextPath}/pages/activities" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/activity.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.all_activities"/>
                            </h1>
                        </a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.authUser != null}">
                    <!-- Portfolio Item 3-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/activities" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/badge.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.mark_time"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </c:if>
                    <tt:hasAuthority authority="ADMIN">
                    <!-- Portfolio Item 4-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/admin/users" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/customer.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.users"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </tt:hasAuthority>
                <tt:hasAuthority authority="ADMIN">
                    <!-- Portfolio Item 5-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/admin/activitiesAdd" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/achievement.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.all_activities"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </tt:hasAuthority>
                <tt:hasAuthority authority="ADMIN">
                    <!-- Portfolio Item 6-->
                    <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/admin/typesAdd" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/type.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.type_add"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </tt:hasAuthority>
                 <tt:hasAuthority authority="ADMIN">       <!-- </div> -->
                <div class="col-md-6 col-lg-4 mb-5">
                        <div class="portfolio-item mx-auto">
                            <a href="${pageContext.request.contextPath}/pages/admin/activitiesRequests" class="text-decoration-none">
                            <div class="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                                <div class="portfolio-item-caption-content text-center text-white"><i class="fas fa-plus fa-3x"></i></div>
                            </div>
                            <img class="img-fluid " src="${pageContext.request.contextPath}/images/award.png" alt="..." />
                            <h1 class="display-6 text-center p-2 retroshd">
                                <fmt:message key="link.requests"/>
                            </h1>
                            </a>
                        </div>
                    </div>
                </tt:hasAuthority>
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
