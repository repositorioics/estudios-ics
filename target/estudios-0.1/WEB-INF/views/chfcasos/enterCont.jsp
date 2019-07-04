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
                            <i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="contact" />
                            
                        </div>
                        <div class="card-block">
                        	<form action="" class="form-horizontal ">
                        		<div class="form-group row">
	                                <label class="col-md-3 form-control-label"><spring:message code="code" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${visita.codigoParticipanteCaso.participante.participante.codigo}"/></p>
	                                </div>
	                            </div>
	                            <div class="form-group row">
	                                <label class="col-md-3 form-control-label" ><spring:message code="participant" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${visita.codigoParticipanteCaso.participante.participante.nombreCompleto}"/> -  <spring:message code="visit" /> <c:out value="${visita.visita}"/> 
	                                     	- <fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy" /></p>
	                                </div>
	                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-block">
                        	<spring:url value="/chf/editarcaso/visitdata/{codigoCasoVisita}" var="visitDataUrl">
                                <spring:param name="codigoCasoVisita" value="${visita.codigoCasoVisita}" />
                            </spring:url>
                        	<form action="#" autocomplete="off" id="cont-form" class="form-horizontal">
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="codigoVisitaCaso"><spring:message code="please.enter"/> <spring:message code="codigoCasoVisita"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="codigoCasoVisita" id="codigoCasoVisita" class="form-control" value="${visita.codigoCasoVisita}"></input>
                                    </div>
                                </div>
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="codigoCasoContacto"><spring:message code="please.enter"/> <spring:message code="codigoCasoContacto"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="codigoCasoContacto" id="codigoCasoContacto" class="form-control" value="${contacto.codigoCasoContacto}"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="fechaContacto"><spring:message code="please.enter"/> <spring:message code="fechaContacto"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="fechaContacto" id="fechaContacto" class="form-control date-picker" value="<fmt:formatDate value="${contacto.fechaContacto}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="partContacto"><spring:message code="partContacto"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="partContacto" id="partContacto" class="form-control select2-single">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${participantes}" var="participante">
												<c:choose> 
													<c:when test="${participante.participante.participante.codigo eq contacto.partContacto.participante.codigo}">
														<option selected value="${participante.participante.participante.codigo}">${participante.participante.participante.codigo} - ${participante.participante.participante.nombreCompleto}</option>
													</c:when>
													<c:otherwise>
														<option value="${participante.participante.participante.codigo}">${participante.participante.participante.codigo} - ${participante.participante.participante.nombreCompleto}</option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="tiempoInteraccion"><spring:message code="tiempoInteraccion"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tiempoInteraccion" id="tiempoInteraccion" class="form-control select2-single">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${tiempoCat}" var="tCat">
												<c:choose> 
													<c:when test="${tCat.catKey eq contacto.tiempoInteraccion}">
														<option selected value="${tCat.catKey}"><spring:message code="${tCat.spanish}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${tCat.catKey}"><spring:message code="${tCat.spanish}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row" id = "tipoInteraccionGroup">
                        			<label class="col-sm-3 form-control-label" for="tipoInteraccion"><spring:message code="tipoInteraccion"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tipoInteraccion" id="tipoInteraccion" class="form-control select2-multiple" multiple>
                                            <c:forEach items="${tipoCat}" var="tipCat">
												<c:choose> 
													<c:when test="${fn:contains(contacto.tipoInteraccion, tipCat.catKey)}">
														<option selected value="${tipCat.catKey}"><spring:message code="${tipCat.spanish}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${tipCat.catKey}"><spring:message code="${tipCat.spanish}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
	                            <div class="form-group">
									<div class="col-md-12">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
										<a href="${fn:escapeXml(visitDataUrl)}" id="finishlink" class="btn btn-danger"><spring:message code="cancel" /></a>
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
	<fmt:formatDate value="${visita.codigoParticipanteCaso.codigoCaso.fechaInicio}" pattern="dd/MM/yyyy" var="fechaInicio" />
	<fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy" var="fechaFin" />
	
	<!-- Custom scripts required by this view -->
	<spring:url value="/resources/scripts/visitas/process-cont.js" var="sintScript" />
	<script src="${sintScript}" type="text/javascript"></script>
	
	<!-- Plugins and scripts required by this views -->
	
	<spring:url value="/resources/js/libs/jquery.maskedinput.min.js" var="jqMasked" />
	<script src="${jqMasked}" type="text/javascript"></script>
		
	<spring:url value="/resources/js/libs/select2.min.js" var="select2Sc" />
	<script src="${select2Sc}" type="text/javascript"></script>
	
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
	
	<spring:url value="/chf/editarcaso/saveCont" var="saveContUrl"/>
	<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
	<script>
	jQuery(document).ready(function() {			
		var parametros = {saveContUrl: "${saveContUrl}",
				processSuccess: "${processSuccess}",
				fechaInicio:"${fechaInicio}",
				fechaFin:"${fechaFin}"};
		CreateCont.init(parametros);
	});
	if ($('#tiempoInteraccion').val() == "5" || $('#tiempoInteraccion').val() == "") $("#tipoInteraccionGroup").hide();
	</script>
	
</body>
</html>