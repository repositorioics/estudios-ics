<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/chf/editarcaso/" htmlEscape="true "/>"><spring:message code="chfcasossearch" /></a>
                </li>
            </ol>
            <div class="container-fluid">
            	<div class="animated fadeIn">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-group"></i> <spring:message code="intensiveMonitoring" /> <spring:message code="house" />
                    									<c:out value="${caso.casa.codigoCHF}" /> - <fmt:formatDate value="${caso.fechaInicio}" pattern="dd/MM/yyyy" />
                            
                        </div>
                        <div class="card-block">
                        	<spring:url value="/chf/editarcaso/newfailvisit/{codigoCaso}/" var="newVisitFallidaUrl">
                                <spring:param name="codigoCaso" value="${caso.codigoCaso}" />
                            </spring:url>
                            <table class="table table-striped table-bordered datatable">
                                <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
		                                <th><spring:message code="participant" /></th>
		                                <th><spring:message code="age" /></th>
		                                <th><spring:message code="positive" /></th>
		                                <th><spring:message code="fif" /></th>
		                                <th><spring:message code="actions" /></th>
                                    </tr>
                                </thead>
                                <tbody>
	                                <c:forEach items="${participantes}" var="parti">
	                                	<spring:url value="/chf/editarcaso/participantdata/{codigo}/" var="editUrl">
		                                    <spring:param name="codigo" value="${parti.codigoCasoParticipante}" />
		                                </spring:url>
		                                <tr>
		                                    <td><c:out value="${parti.participante.participante.codigo}" /></td>
		                                    <td><c:out value="${parti.participante.participante.nombreCompleto}" /></td>
		                                    <td><c:out value="${parti.participante.participante.edad}" /></td>
		                                    <c:choose>
		                                        <c:when test="${parti.enfermo eq 'S'}">
		                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
		                                        </c:when>
		                                        <c:otherwise>
		                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
		                                        </c:otherwise>
		                                    </c:choose>
		                                    <td><fmt:formatDate value="${parti.fechaEnfermedad}" pattern="dd/MM/yyyy" /></td>
		                                    <td><a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a></td>
		                                </tr>
		                            </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="${fn:escapeXml(newVisitFallidaUrl)}" class="btn btn-sm btn-danger"><i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="failVisit" /></a>
                        </div>
                    </div>
                </div>
            	
            </div>
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../fragments/bodyFooter.jsp" />
    <jsp:include page="../fragments/corePlugins.jsp" />
    
    <!-- GenesisUI main scripts -->
	<spring:url value="/resources/js/app.js" var="App" />
	<script src="${App}" type="text/javascript"></script>
</body>
</html>