<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<!-- DATE PICKER -->
<spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
<link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
<!-- END DATE PICKER -->
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
                            <i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="visit" />
                            
                        </div>
                        <div class="card-block">
                        	<form action="" class="form-horizontal ">
                        		<div class="form-group row">
	                                <label class="col-md-3 form-control-label"><spring:message code="code" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${participante.participante.participante.codigo}"/></p>
	                                </div>
	                            </div>
	                            <div class="form-group row">
	                                <label class="col-md-3 form-control-label" ><spring:message code="participant" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${participante.participante.participante.nombreCompleto}"/></p>
	                                </div>
	                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-block">
                        	<spring:url value="/chf/editarcaso/participantdata/{codigo}/" var="partDataUrl">
                                <spring:param name="codigo" value="${participante.codigoCasoParticipante}" />
                            </spring:url>
                        	<form action="#" autocomplete="off" id="visit-form" class="form-horizontal">
                        		<div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group" hidden="true">
	                                        <label class="form-control-label" for="codigoParticipanteCaso"><spring:message code="please.enter"/> <spring:message code="codigoParticipanteCaso"/><span class="required">*</span></label>
	                                        <input type="text" readonly name="codigoParticipanteCaso" id="codigoParticipanteCaso" class="form-control" value="${participante.codigoCasoParticipante}"></input>
	                                    </div>
	                                </div>
	                            </div>
                        		<div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group" hidden="true">
	                                        <label class="form-control-label" for="codigoCasoVisita"><spring:message code="please.enter"/> <spring:message code="codigoCasoVisita"/><span class="required">*</span></label>
	                                        <input type="text" readonly name="codigoCasoVisita" id="codigoCasoVisita" class="form-control" value="${visita.codigoCasoVisita}"></input>
	                                    </div>
	                                </div>
	                            </div>
                        		<div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="visita"><spring:message code="please.enter"/> <spring:message code="visita"/><span class="required">*</span></label>
	                                        <select name="visita" id="visita" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${visitas}" var="visitaCat">
													<c:choose> 
														<c:when test="${visitaCat.catKey eq visita.visita}">
															<option selected value="${visitaCat.catKey}"><spring:message code="${visitaCat.spanish}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${visitaCat.catKey}"><spring:message code="${visitaCat.spanish}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                            </div>
	                        	<div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="fechaVisita"><spring:message code="please.enter"/> <spring:message code="visitDate"/><span class="required">*</span></label>
	                                        <input type="text" name="fechaVisita" id="fechaVisita" class="form-control date-picker" value="<fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="horaVisita"><spring:message code="please.enter"/> <spring:message code="visitTime"/><span class="required">*</span></label>
	                                        <input type="text" name="horaVisita" id="horaVisita" class="form-control" 
	                                        		value="<fmt:formatDate value="${visita.fechaVisita}" pattern="hh:mm" />" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" required="required" placeholder="hh:mm"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="horaProbableVisita"><spring:message code="please.enter"/> <spring:message code="visitHour"/><span class="required">*</span></label>
	                                        <input type="text" name="horaProbableVisita" id="horaProbableVisita" 
	                                        			value="${visita.horaProbableVisita}" class="form-control" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" required="required" placeholder="hh:mm"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="expCS"><spring:message code="expCS"/><span class="required">*</span></label>
	                                        <select name="expCS" id="expCS" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${sino}" var="sinoCat">
													<c:choose> 
														<c:when test="${sinoCat.catKey eq visita.expCS}">
															<option selected value="${sinoCat.catKey}"><spring:message code="${sinoCat.spanish}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${sinoCat.catKey}"><spring:message code="${sinoCat.spanish}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                            </div>
								<div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="temp"><spring:message code="please.enter"/> <spring:message code="temp"/><span class="required">*</span></label>
	                                        <input type="text" name="temp" id="temp" class="form-control" value="${visita.temp}"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="form-group">
									<div class="col-md-12">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
										<a href="${fn:escapeXml(partDataUrl)}" id="finishlink" class="btn btn-danger"><spring:message code="cancel" /></a>
									</div>
								</div>
                        	</form>
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
	
	<c:set var="lenguaje" value="es"/>
	<fmt:formatDate value="${participante.codigoCaso.fechaInicio}" pattern="dd/MM/yyyy" var="fechaInicio" />
	
	<!-- Custom scripts required by this view -->
	<spring:url value="/resources/scripts/visitas/process-visit.js" var="visitScript" />
	<script src="${visitScript}" type="text/javascript"></script>
	
	<!-- Plugins and scripts required by this views -->
		
	<spring:url value="/resources/js/libs/jquery.maskedinput.min.js" var="jqMasked" />
	<script src="${jqMasked}" type="text/javascript"></script>
	
	<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
	<script src="${loadingButtonsJs}" type="text/javascript"></script>
	
	<spring:url value="/resources/js/libs/jquery.validate.js" var="jqValidation" />
	<script src="${jqValidation}" type="text/javascript"></script>
	
	<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	    <spring:param name="language" value="${lenguaje}" />
	</spring:url>
	<script src="${jQValidationLoc}"></script>
	
	<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
	<script src="${validateAMJs}" type="text/javascript"></script>
		
	<!-- bootstrap datepicker -->
	<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
	<script src="${datepickerPlugin}"></script>
	
	<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
	    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
	<script src="${datePickerLoc}"></script>
	
	<spring:url value="/chf/editarcaso/saveVisit" var="saveVisitUrl"/>
	<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
	<script>
		jQuery(document).ready(function() {			
			var parametros = {saveVisitUrl: "${saveVisitUrl}",
					processSuccess: "${processSuccess}",
					fechaInicio:"${fechaInicio}"};
			CreateVisit.init(parametros);
		});
		
	</script>
	
</body>
</html>