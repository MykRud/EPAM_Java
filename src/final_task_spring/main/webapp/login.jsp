<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <style type="text/css">
            ::placeholder{
                font-size: 5px;
            }
        </style>

    </head>
    <body id="page-top">
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>
    
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">
                    <spring:message code="button.signin"/>
                </h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-xl-7">
                <span class="text-danger align-content-center">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        ${error}<br>
                                    </c:forEach>
                                </span>
                            </div>
                        </div>
                        ${SPRING_SECURITY_LAST_EXCEPTION.message}
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form id="contactForm" action="login" method="post" data-sb-form-api-token="API_TOKEN">
                            <c:if test="${requestScope.error}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <span>
                                        <spring:message code="login.error"/>
                                    </span>
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:if>
                            <!-- Username input-->
                            <div class="form-floating mb-3">
                                <spring:message code="login.placeholder.username" var="userPl"/>
                                <input 
                                class="form-control pt-4 p-2" 
                                id="name" 
                                type="text" 
                                name="username" 
                                placeholder="userPl"
                                data-sb-validations="required" />
                                <label for="name">
                                    <spring:message code="login.label.username"/>
                                </label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.username"/>
                                </div>
                            </div>
                        
                            <!-- Password input-->
                            <div class="form-floating mb-3">
                                <spring:message code="login.label.password" var="passwordPl"/>
                                <input class="form-control pt-4 p-2" id="password" name="password" type="password" placeholder="passwordPl" data-sb-validations="required" />
                                <label for="name">
                                    <spring:message code="login.label.password"/>
                                </label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.password"/>
                                </div>
                            </div>
                            
                            
                        
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-3">
                                    <div class="fw-bolder">
                                        <spring:message code="login.successful.label"/>                                    
                                    </div>
                                    <br />
                                </div>
                            </div>
                           
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit">
                                <spring:message code="button.signin"/> 
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        
        <%@include file="/WEB-INF/fragments/footer.jspf" %>
        

        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <script src="<c:url value="/resources/js/scripts.js"/>"></script>

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
