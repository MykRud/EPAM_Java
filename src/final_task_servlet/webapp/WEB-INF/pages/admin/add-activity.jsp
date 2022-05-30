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
    </head>
    <body id="page-top">
        
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>
    
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">Add activity</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                    <span class="text-danger">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        ${error}<br>
                                    </c:forEach>
                                </span>
                </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form id="contactForm" method="post" action="${pageContext.request.contextPath}/pages/activitiesAdd" data-sb-form-api-token="API_TOKEN">
                            <!-- input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" type="text" name="name"
                                       value="${requestScope.activity.name}"
                                       placeholder="<fmt:message key="activities.add.activity_name.placeholder"/>" data-sb-validations="required" />
                                <label for="name"><fmt:message key="activities.add.activity_name.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <fmt:message key="login.required.name"/>
                                </div>
                            </div>
                            
                            <div class="form-floating mb-3">
                                <textarea class="form-control" id="name" type="text" name="description"
                                          placeholder="<fmt:message key="activities.add.description.placeholder"/>"
                                          class="form-control">${requestScope.activity.description} data-sb-validations="required" /></textarea>
                                <label for="name"><fmt:message key="activities.add.description.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <fmt:message key="login.required.description"/>
                                </div>
                            </div>
                           
                            <div class="form-floating mb-3">

                                
                                <section class="pb-2">
     
                                    <label for="name">
                                        <fmt:message key="activities.add.type.label"/>
                                    </label>

                        <!-- AUTO COMPLETE DROPDOWN -->
                        <select class="selectpicker form-control border-0 mb-1 mt-3 px-4 py-4 rounded shadow" name="type" id="type">
                            <c:forEach items="${requestScope.types}" var="type">
                                        <option value="${type.getName()}">
                                                ${type.toString()}
                                        </option>
                                    </c:forEach>
                        </select>
         
                            </div>
                           
                            
                        
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-2">
                                    <div class="fw-bolder">
                                        <fmt:message key="login.successful.label"/>
                                    </div>
                                    <br />
                                </div>
                            </div>
                            <!-- Submit error message-->
                            <!---->
                            <!-- This is what your users will see when there is-->
                            <!-- an error submitting the form-->
                            <!-- Submit Button-->
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit" value="<fmt:message key="activities.add.button.submit"/>"><fmt:message key="types.add.button.submit"/></button>
                        </form>
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
