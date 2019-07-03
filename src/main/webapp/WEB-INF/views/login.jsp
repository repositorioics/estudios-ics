<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<jsp:include page="fragments/headTag.jsp" />
</head>
<body class="app flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card-group mb-0">
                    <div class="card p-2">
                        <div class="card-block">
	                        <!-- BEGIN LOGIN FORM -->
							<form class="login-form" action="j_spring_security_check" method="post">
								<h1><spring:message code="login" /></h1>
								<p class="text-muted"><spring:message code="login.message" /></p>
								<c:if test="${not empty error}">
									<div class="alert-danger">
										<c:choose>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='Bad credentials'}">
												<spring:message  code="login.badCredentials" />
											</c:when>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User account is locked'}">
												<spring:message  code="login.accountLocked" />
											</c:when>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User account has expired'}">
												<spring:message  code="login.accountExpired" />
											</c:when>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User credentials have expired'}">
												<spring:message  code="login.credentialsExpired" />
											</c:when>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User is disabled'}">
												<spring:message  code="login.userDisabled" />
											</c:when>
											<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='Maximum sessions of 3 for this principal exceeded'}">
												<spring:message  code="login.maxSessionsOut" />
											</c:when>
											<c:otherwise>
												${SPRING_SECURITY_LAST_EXCEPTION.message}
											</c:otherwise>
										</c:choose>
									</div>
								</c:if>
								<div class="input-group mb-1">
	                                <span class="input-group-addon"><i class="icon-user"></i>
	                                </span>
	                                <input type="text" autocomplete="off" autofocus="true" class="form-control" placeholder="<spring:message code="login.username" />" name="j_username">
	                            </div>
	                            <div class="input-group mb-2">
	                                <span class="input-group-addon"><i class="icon-lock"></i>
	                                </span>
	                                <input type="password" autocomplete="off" class="form-control" placeholder="<spring:message code="login.password" />" name="j_password">
	                            </div>
		                        <div class="row">
	                                <div class="col-6">
	                                    <button type="submit" class="btn btn-primary px-2"><spring:message code="login"/></button>
	                                </div>
	                                <div class="col-6 text-right">
	                                    <a href="<spring:url value="/" htmlEscape="true "/>" class="btn btn-link px-0 actEng"><spring:message code="login.forgot.password"/></a>
	                                </div>
	                            </div>
	                            <div class="row">
	                            	<div class="col-12 text-right">
	                            		<a href="<spring:url value="/" htmlEscape="true "/>" class="actEng"> English </a>|<a href="<spring:url value="/" htmlEscape="true "/>" class="actEsp"> Español </a>
	                            	</div>
	                            </div>
							</form>
                        </div>
                    </div>
                    <div class="card card-inverse card-primary py-3 hidden-md-down" style="width:44%">
                        <div class="card-block text-center">
                            <div>
                                <h2><spring:message code="title"/></h2>
                                <p><spring:message code="heading"/></p>
                                <!-- BEGIN LOGO -->
								<div>
									<spring:url value="/resources/img/logo-login.png" var="logo" />
									<img src="${logo}" alt="<spring:message code="login" />" />
								</div>
								<!-- END LOGO -->
                                <div class="copyright">2016 &copy; <spring:message code="footer"/>.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="fragments/corePlugins.jsp" />
</body>
</html>