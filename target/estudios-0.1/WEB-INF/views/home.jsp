<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<jsp:include page="fragments/headTag.jsp" />
    <style>
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	<jsp:include page="fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="fragments/sideBar.jsp" />
        <!-- Main content -->
        <div class="main">
        	<!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a></li>
            </ol>
            <div class="container-fluid">
                <!--  <strong>  <sec:authentication property="principal.authorities" /></strong> -->
                <!--  <div class="jumbotron">
                    <h1>Hola, <strong> <c:out value="${pageContext.request.remoteUser}"/></strong></h1>
                </div>-->
            </div>
        </div>
    </div>
    <jsp:include page="fragments/bodyFooter.jsp" />
    <jsp:include page="fragments/corePlugins.jsp" />
    <!-- GenesisUI main scripts -->
	<spring:url value="/resources/js/app.js" var="App" />
	<script src="${App}" type="text/javascript"></script>
</body>
</html>