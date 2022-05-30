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
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">Activities</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <div class="card">
        <div class="card-header">
            <c:if test="${!requestScope.activites.isEmpty()}">
                <h1 class="display-4"><fmt:message key="activities.title"/></h1>
                <hr>
                <%@include file="/WEB-INF/fragments/activities-paginator.jspf" %>
            </c:if>
            <c:if test="${requestScope.activites.isEmpty()}">
                <h1 class="display-4"><fmt:message key="activities.empty"/></h1>
                <hr>
            </c:if>
            <span class="text-danger">
                                                        <c:forEach var="error" items="${requestScope.errors}">
                                                            ${error}<br>
                                                        </c:forEach>
                                                    </span>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/pages/activities">
        <div class="form-group col-md-6">
            
                <label class="col-form-label" for="type">
                    <fmt:message key="activities.sorting.label"/>
                </label>
                <select name="sort-methods" class="form-control" id="type">
                    <option value="by-name" name="by-name">
                        <fmt:message key="activities.sorting.method.by.name"/>
                    </option>
                    <option value="by-category" name="by-category">
                        <fmt:message key="activities.sorting.method.by.category"/>
                    </option>
                    <option value="by-users" name="by-users">
                        <fmt:message key="activities.sorting.method.by.number.of.users"/>
                    </option>
                </select>
                </div>
                <div class="col">
                    <input class="btn btn-primary" value="<fmt:message key="sort.button.submit"/>"
                        type="submit">
                    </div>
                </form>
            <br>
            <br>
        
            <br>
        <div class="card-body">
            <div class="row">
                <c:forEach items="${requestScope.activities}" var="activity">
                    <div class="col-md-6">
                        <div class="card mb-3 shadow-sm">
                            <div class="card-header">
                                <p>${activity.name}</p>
                            </div>
                            <div class="card-body">
                                <p>${activity.description}</p>
                            </div>
                            <div class="card-footer">
                                <p>
                                    <span><fmt:message key="activities.activity.id"/></span>
                                    <span>${activity.getId()}</span>
                                </p>
                                <p>
                                    <span><fmt:message key="activities.activity.type"/></span>
                                    <span>${activity.type.toString()}</span>
                                </p>
                                <p>
                                    <span><fmt:message key="activities.activity.start_time"/></span>
                                    <span>
                                        
                                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${activity.startTime}"/>
                                    </span>
                                </p>
                                <p>
                                    <span><fmt:message key="activities.activity.end_time"/></span>
                                    <span>
                                        
                                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${activity.endTime}"/>
                                    </span>
                                </p>
                                <p>
                                    <span><fmt:message key="activities.activity.duration"/></span>
                                    <span>
                                            ${activity.getDaysDuration()}
                                    </span>
                                    <span><fmt:message key="activities.activity.duration.days"/></span>
                                    <span>
                                            ${activity.getHoursDuration()}
                                    </span>
                                    <span><fmt:message key="activities.activity.duration.hours"/></span>
                                    <span>
                                            ${activity.getMinutesDuration()}
                                    </span>
                                    <span><fmt:message key="activities.activity.duration.minutes"/></span>
                                </p>
                                <p>
                                    <span><fmt:message key="activities.activity.users"/></span>
                                    <c:forEach items="${activity.users}" var="user">
                                        <span>${user.username}</span><span>;</span>
                                    </c:forEach>
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div aria-label="btn-group" class="btn-group" role="group">
                                        <tt:hasAuthority authority="ADMIN">
                                            <a class="btn btn-sm btn-secondary"
                                               href="${pageContext.request.contextPath}/pages/admin/activityDelete?activity_id=${activity.id}">
                                                <fmt:message key="activities.activity.button.delete"/>
                                            </a>
                                        </tt:hasAuthority>
                                        <a class="btn btn-sm btn-secondary"
                                           href="${pageContext.request.contextPath}/pages/activityRequestAdd?activity_id=${activity.id}">
                                            <fmt:message key="activities.activity.button.request_to_add"/>
                                        </a>
                                        <a class="btn btn-sm btn-secondary"
                                           href="${pageContext.request.contextPath}/pages/activityRequestComplete?activity_id=${activity.id}">
                                            <fmt:message key="activities.activity.button.request_to_complete"/>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                                                data-target="#timeSpentModal${activity.id}">
                                            <fmt:message key="activities.activity.button.mark_time_spent"/>
                                        </button>
                                    </div>
                                </div>
                                <!--Start modal window-->
                                <div aria-hidden="true" aria-labelledby="timeSpentModalTitle" class="modal fade"
                                     id="timeSpentModal${activity.id}" role="dialog" tabindex="-1">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="timeSpentModalTitle">
                                                    <fmt:message key="activities.modal.title"/>
                                                </h5>
                                                <button aria-label="Close" class="close" data-dismiss="modal"
                                                        type="button">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/pages/markTime?activity_id=${activity.id}">
                                                <div class="modal-body">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">
                                                                <fmt:message key="activities.modal.days"/>
                                                            </span>
                                                        </div>
                                                        <input aria-label="days" class="form-control"
                                                               name="days"
                                                               required
                                                               type="number">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">
                                                                <fmt:message key="activities.modal.hours"/>
                                                            </span>
                                                        </div>
                                                        <input aria-label="hours" class="form-control"
                                                               name="hours"
                                                               required
                                                               type="number">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">
                                                                <fmt:message key="activities.modal.minutes"/>
                                                            </span>
                                                        </div>
                                                        <input aria-label="minutes" class="form-control"
                                                               name="minutes"
                                                               required
                                                               type="number">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button class="btn btn-secondary" data-dismiss="modal"
                                                            type="button">
                                                        <fmt:message key="activities.modal.button.close"/>
                                                    </button>
                                                    <button class="btn btn-primary" type="submit">
                                                        <fmt:message key="activities.modal.button.confirm"/>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!--End modal window-->
                                <small>
                                    <span><fmt:message key="activities.activity.status"/></span>
                                    <span>
                                            ${activity.status.toString()}
                                    </span>
                                </small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="card-footer">
            <%@include file="/WEB-INF/fragments/activities-paginator.jspf" %>
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
