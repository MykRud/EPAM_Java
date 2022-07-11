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
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
        <link href="${pageContext.request.contextPath}/css/newStyle.css" rel="stylesheet" />

    </head>
    <body id="page-top">
        
       <%@include file="/WEB-INF/fragments/navbar.jspf" %>
    
    
        <!-- Contact Section-->
        <section class="page-section" id="portfolio">
            <div class="container title-section">
                <!-- Portfolio Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">
                    <fmt:message key="activities.title"/>
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
                <!-- Portfolio Grid Items-->
                
                <div class="row justify-content-center">
                    <div class="col-md-8">
                    <c:if test="${!requestScope.activites.isEmpty()}">
                        <%@include file="/WEB-INF/fragments/activities-paginator.jspf" %>
                    </c:if>
                    <c:if test="${requestScope.activites.isEmpty()}">
                        <h1 class="display-4"><fmt:message key="activities.empty"/></h1>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <form method="post" action="${pageContext.request.contextPath}/pages/activities">
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
                
                <div class="col mt-2">
                    <input class="btn btn-primary" value="<fmt:message key="sort.button.submit"/>"
                        type="submit">
                    </div>
                </form>
            </div>
            
</div>
        </div>
    </section>
    <br>

    <section class="page-section portfolio" id="portfolio">
        <div class="container">
            <div class="row justify-content-center">        
                <c:forEach items="${requestScope.activities}" var="activity">
                    <!-- Portfolio Item 2-->
                    <div class="col-md-4">
                        <div class="card mb-3" >
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
                                        <timeManage:authorityCheck auth="ADMIN">
                                            <a class="btn btn-sm btn-secondary"
                                               href="${pageContext.request.contextPath}/pages/admin/activityDelete?activity_id=${activity.id}">
                                                <fmt:message key="activities.activity.button.delete"/>
                                            </a>
                                        </timeManage:authorityCheck>
                                        <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/pages/activityRequestAdd?activity_id=${activity.id}">
                                            <fmt:message key="activities.activity.button.request_to_add"/>
                                        </a>
                                        <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/pages/activityRequestComplete?activity_id=${activity.id}">
                                            <fmt:message key="activities.activity.button.request_to_complete"/>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#timeSpentModal${activity.id}">
                                            <fmt:message key="activities.activity.button.mark_time_spent"/>
                                        </button>
                                    </div>
                                </div>

                                <!--Start modal window-->
                                
<!-- Modal -->
<div aria-hidden="true" aria-labelledby="timeSpentModalTitle" class="modal fade" id="timeSpentModal${activity.id}" role="dialog" tabindex="-1">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="timeSpentModalTitle">
                                                    <fmt:message key="activities.modal.title"/>
                                                </h5>
                                                <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <form method="post" action="${pageContext.request.contextPath}/pages/markTime?activity_id=${activity.id}">
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
                                                    <button class="btn btn-secondary" data-dismiss="modal" type="button">
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
    </section>
        

<%@include file="/WEB-INF/fragments/footer.jspf" %>

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}/js/scripts.js"></script>

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    </body>
</html>
