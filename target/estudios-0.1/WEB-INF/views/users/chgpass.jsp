<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	<jsp:include page="../fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="../fragments/sideBar.jsp" />
        <!-- Main content -->
        <div class="main">

        	<!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/users/chgpass" htmlEscape="true "/>"><spring:message code="changepass" /></a>
                </li>
            </ol>
            <spring:url value="/users/chgPass" var="chgPassUrl"/>
            <spring:url value="/users/profile" var="usuarioUrl"/>
            <c:set var="passUpdated"><spring:message code="pass.updated" /></c:set>
            <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            <c:set var="userName"><sec:authentication property="principal.username" /></c:set>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <spring:message code="users" /> <small><spring:message code="edit" /></small>
                        </h3>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-8">
                                <form action="#" autocomplete="off" id="chg-pass-form" class="form-horizontal" style="border: 1px">
                                        <div class="form-group row">
                                            <label class="form-control-label col-md-3" for="username"><spring:message code="username" />
										        <span class="required">*</span>
                                            </label>
                                            <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                                <input id="username" name="username" type="text" readonly value="${userName}" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group row password-strength">
                                            <label class="form-control-label col-md-3" for="password"><spring:message code="login.password" />
                                                <span class="required"> * </span>
                                            </label>
                                                <div class="input-group col-md-9">
                                                    <span class="input-group-addon">
													    <i class="fa fa-lock"></i>
                                                    </span>
                                                    <input id="password" name="password" type="password" class="form-control"/>
                                                </div>
											<!--<span class="help-block">
												 <spring:message code="Pattern.password.format" />
											</span>-->
                                        </div>
                                        <div class="form-group row">
                                            <label class="form-control-label col-md-3" for="confirm_password"><spring:message code="password.repeat" />
                                                <span class="required">
                                                    *
                                                </span>
                                            </label>
                                            <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-lock"></i>
												</span>
                                                <input id="confirm_password" name="confirm_password" type="password" class="form-control"/>
                                            </div>
                                        </div>
                                    <div class="form-actions fluid">
                                        <div class="col-md-offset-6 col-md-6">
                                            <button id="guardar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="save" /></button>
                                            <a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger btn-ladda" data-style="expand-right"><spring:message code="cancel" /></a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-2">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../fragments/bodyFooter.jsp" />
    <jsp:include page="../fragments/corePlugins.jsp" />
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <!-- jQuery Password Meter-->
    <spring:url value="/resources/js/libs/pwstrength.js" var="pwStrength" />
    <script src="${pwStrength}" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- GenesisUI main scripts -->
	<spring:url value="/resources/js/app.js" var="App" />
	<script src="${App}" type="text/javascript"></script>
    <c:choose>
        <c:when test="${cookie.eIcsLang.value == null}">
            <c:set var="lenguaje" value="es"/>
        </c:when>
        <c:otherwise>
            <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
        </c:otherwise>
    </c:choose>
    <spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
    <script src="${validateJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
    <script src="${validateAMJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/views/users/chgpass-user.js" var="chgPassScript" />
    <script src="${chgPassScript}" type="text/javascript"></script>
    <spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
    <script src="${loadingButtonsJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <script src="${jQValidationLoc}"></script>
    <script>
        jQuery(document).ready(function() {
            var parameters = {chgPassUrl: "${chgPassUrl}",
                usuarioUrl: "${usuarioUrl}",
                successmessage: "${passUpdated}",
                error: "${errorProcess}"
            };
            ChgPass.init(parameters);
            $('#password').focus();
        });
    </script>
</body>
</html>