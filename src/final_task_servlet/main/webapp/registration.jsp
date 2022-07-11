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
        <link rel="icon" type="${pageContext.request.contextPath}/image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
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
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">
                    <fmt:message key="registration.header"/>
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
                    <c:if test="${not empty result}">
                        <h5><c:out value="${result}"></c:out></h5>
                     </c:if>
                                    <c:forEach items="${sessionScope.errors}" var="error">
                                        ${error}<br>
                                    </c:forEach>
                                    <c:remove var="errors" scope="session" />
                                </span>
                            </div>
                        </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form id="contactForm" action="${pageContext.request.contextPath}/pages/registration" method="post" data-sb-form-api-token="API_TOKEN">
                            <!-- First Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="first_name" name="first_name"
                                   value="${requestScope.user.firstName}" type="text" placeholder="<fmt:message key="registration.first_name.placeholder"/>" data-sb-validations="required" />
                                <label for="name">
                                    <fmt:message key="registration.first_name.placeholder"/>
                                </label>
                                <div class="invalid-feedback" data-sb-feedback="first_name:required">
                                    <fmt:message key="login.required.first_name"/>
                                </div>
                            </div>
                            <!-- Last Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="last_name" name="last_name"
                                   value="${requestScope.user.lastName}" type="text" placeholder="<fmt:message key="registration.last_name.placeholder"/>" data-sb-validations="required" />
                                <label for="name">Last name</label>
                                <div class="invalid-feedback" data-sb-feedback="last_name:required">
                                    <fmt:message key="login.required.last_name"/>
                                </div>
                            </div>
                            <!-- Username input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="username_reg" type="text" name="username_reg"
                                   placeholder="<fmt:message key="registration.username.placeholder"/>" data-sb-validations="required" />
                                <label for="name"><fmt:message key="registration.username.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="username_reg:required">
                                    <fmt:message key="login.required.username"/>
                                </div>
                            </div>
                            <!-- Password input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="password_reg" type="password" name="password_reg"
                                   placeholder="<fmt:message key="registration.password.placeholder"/>" data-sb-validations="required" />
                                <label for="name"><fmt:message key="registration.password.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="password_reg:required">
                                    <fmt:message key="login.required.password"/>
                                </div>
                            </div>
                            <!-- Age input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="age" type="text" name="age"
                                   value="${requestScope.user.age}"
                                   placeholder="<fmt:message key="registration.age.placeholder"/>" data-sb-validations="required" />
                                <label for="age"><fmt:message key="registration.age.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="age:required">
                                    <fmt:message key="login.required.age"/>
                                </div>
                            </div>
                            <!-- Gender input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="gender" type="text" name="gender"
                                   value="${requestScope.user.gender}"
                                   placeholder="<fmt:message key="registration.gender.placeholder"/>"
                                     data-sb-validations="required" />
                                <label for="age"><fmt:message key="registration.gender.label"/></label>
                                <div class="invalid-feedback" data-sb-feedback="gender:required">
                                    <fmt:message key="login.required.gender"/>
                                </div>
                            </div>    
                            <!-- Contact input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="contact" type="tel" name="contact"
                                   value="${requestScope.contact.contact}"
                                   placeholder="<fmt:message key="registration.contact.placeholder"/>" data-sb-validations="required" />
                                <label for="phone"><fmt:message key="registration.contact.label"/> (+381234567890)</label>
                                <div class="invalid-feedback" data-sb-feedback="contact:required">
                                    <fmt:message key="login.required.contact"/>
                                </div>
                            </div>
                            
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-3">
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
                            <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error!</div></div>
                            <!-- Submit Button-->
                            <input class="btn btn-primary btn-xl"
                                       value="<fmt:message key="button.signup"/>"
                                       type="submit">
                        </form>
                    </div>
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
