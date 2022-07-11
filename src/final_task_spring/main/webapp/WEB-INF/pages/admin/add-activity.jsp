<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </head>
    <body id="page-top">
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>
    
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">
                    <spring:message code="link.add_activity"/>
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
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form:form modelAttribute="activity" id="contactForm" method="post" action="activitiesAdd" data-sb-form-api-token="API_TOKEN">
                            <!-- input-->
                            <div class="form-floating mb-3">
                                <form:input path="name" class="form-control" id="name" type="text" name="name"
                                       value="${name}"
                                       data-sb-validations="required" />
                                <form:label path="name" for="name">
                                    <spring:message code="activities.add.activity_name.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.name"/>
                                </div>
                            </div>
                            
                            <div class="form-floating mb-3">
                                <form:textarea 
                                path="description" 
                                class="form-control" 
                                data-sb-validations="required" />
                                <form:label path="description" for="description">
                                    <spring:message code="activities.add.description.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.description"/>
                                </div>
                            </div>
                           
                            <div class="form-floating mb-3">

                                
                                <section class="pb-2">
     

                        <!-- AUTO COMPLETE DROPDOWN -->
                        <form:select path="type" class="selectpicker form-control border-0 mb-1 mt-3 px-4 py-4 rounded shadow" name="type" id="type">
                            <c:forEach items="${types}" var="currentType">
                                        <form:option value="${currentType}">
                                                ${currentType.toString()}
                                        </form:option>
                                    </c:forEach>
                        </form:select>
         
                            </div>
                           
                            
                        
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-2">
                                    <div class="fw-bolder">
                                        <spring:message code="login.successful.label"/>
                                    </div>
                                    <br />
                                </div>
                            </div>
                            <!-- Submit error message-->
                            <!---->
                            <!-- This is what your users will see when there is-->
                            <!-- an error submitting the form-->
                            <!-- Submit Button-->
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit" value="<spring:message code="activities.add.button.submit"/>">
                            <spring:message code="types.add.button.submit"/>
                        </button>
                        </form:form>
                    </div>
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
