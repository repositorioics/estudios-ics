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
	                                    <p class="form-control-static"><c:out value="${visita.codigoParticipanteCaso.participante.participante.nombreCompleto}"/> -  <spring:message code="visitaFinal" /> 
	                                     	- <fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy" /></p>
	                                </div>
	                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-block">
                        	<spring:url value="/chf/editarcaso/participantdata/{codigoCasoVisita}/" var="participantDataUrl">
                                <spring:param name="codigoCasoVisita" value="${visita.codigoParticipanteCaso.codigoCasoParticipante}" />
                            </spring:url>
                        	<form action="#" autocomplete="off" id="samp-form" class="form-horizontal">
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="participante"><spring:message code="please.enter"/> <spring:message code="participante"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="participante" id="participante" class="form-control" value="${visita.codigoParticipanteCaso.participante.participante.codigo}"></input>
                                    </div>
                                </div>
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="codigo"><spring:message code="please.enter"/> <spring:message code="codigo"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="codigo" id="codigo" class="form-control" value="${muestra.codigo}"></input>
                                    </div>
                                </div>
                                <div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="fechaVisita"><spring:message code="please.enter"/> <spring:message code="fechaVisita"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="fechaVisita" id="fechaVisita" class="form-control" value="<fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy HH:mm" />"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="tipoMuestra"><spring:message code="tipoMuestra"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tipoMuestra" id="tipoMuestra" class="form-control select2-single">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${catTipoMx}" var="tipoMx">
												<c:choose> 
													<c:when test="${tipoMx.catKey eq muestra.tipoMuestra}">
														<option selected value="${tipoMx.catKey}"><spring:message code="${tipoMx.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${tipoMx.catKey}"><spring:message code="${tipoMx.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row" id="tuboGroup">
                        			<label class="col-sm-3 form-control-label" for="tubo"><spring:message code="tubo"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tubo" id="tubo" class="form-control select2-single">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${catTipoTubo}" var="tipoTubo">
												<c:choose> 
													<c:when test="${tipoTubo.catKey eq muestra.tubo}">
														<option selected value="${tipoTubo.catKey}"><spring:message code="${tipoTubo.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${tipoTubo.catKey}"><spring:message code="${tipoTubo.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="tomaMxSn"><spring:message code="tomaMxSn"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tomaMxSn" id="tomaMxSn" class="form-control select2-single">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${catSiNo}" var="siNo">
												<c:choose> 
													<c:when test="${siNo.catKey eq muestra.tomaMxSn}">
														<option selected value="${siNo.catKey}"><spring:message code="${siNo.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${siNo.catKey}"><spring:message code="${siNo.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div id="sinMxGroup">
	                                <div class="form-group row" id="razonNoTomaSangGroup">
	                        			<label class="col-sm-3 form-control-label" for="razonNoTomaSang"><spring:message code="razonNoToma"/><span class="required">*</span></label>
	                                    <div class="col-sm-3">
	                                        <select name="razonNoTomaSang" id="razonNoTomaSang" class="form-control select2-single">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${catNoMxSangre}" var="noMxSangre">
													<c:choose> 
														<c:when test="${noMxSangre.catKey eq muestra.razonNoToma}">
															<option selected value="${noMxSangre.catKey}"><spring:message code="${noMxSangre.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${noMxSangre.catKey}"><spring:message code="${noMxSangre.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="form-group row" id="razonNoTomaRespGroup">
	                        			<label class="col-sm-3 form-control-label" for="razonNoTomaResp"><spring:message code="razonNoToma"/><span class="required">*</span></label>
	                                    <div class="col-sm-3">
	                                        <select name="razonNoTomaResp" id="razonNoTomaResp" class="form-control select2-single">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${catNoMxResp}" var="noMxResp">
													<c:choose> 
														<c:when test="${noMxResp.catKey eq muestra.razonNoToma}">
															<option selected value="${noMxResp.catKey}"><spring:message code="${noMxResp.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${noMxResp.catKey}"><spring:message code="${noMxResp.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="form-group row" id="otraRazonGroup">
	                                	<label class="col-sm-3 form-control-label" for="descOtraRazonNoToma"><spring:message code="descOtraRazonNoToma"/><span class="required">*</span></label>
	                                	<div class="col-sm-3">
	                                		<input type="text" name="descOtraRazonNoToma" id="descOtraRazonNoToma" class="form-control" value="${muestra.descOtraRazonNoToma}"></input>
	                                	</div>
		                            </div>
                                </div>
                                <div id="conMxGroup">
                                	<div class="form-group row">
	                                	<label class="col-sm-3 form-control-label" for="hora"><spring:message code="hora"/><span class="required">*</span></label>
	                                	<div class="col-sm-3">
	                                		<input type="text" name="hora" id="hora" class="form-control" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" 
	                                							value="${muestra.hora}"></input>
	                                	</div>
		                            </div>
	                                <div class="form-group row" id="codigoMxGroup">
	                                	<label class="col-sm-3 form-control-label" for="codigoMx"><spring:message code="codigoMx"/><span class="required">*</span></label>
	                                	<div class="col-sm-3">
	                                		<input type="text" name="codigoMx" id="codigoMx" class="form-control" value="${muestra.codigoMx}"></input>
	                                	</div>
		                            </div>
		                            <div class="form-group row">
	                                	<label class="col-sm-3 form-control-label" for="volumen"><spring:message code="volumen"/><span class="required">*</span></label>
	                                	<div class="col-sm-3">
	                                		<input type="text" name="volumen" id="volumen" class="form-control" value="${muestra.volumen}"></input>
	                                	</div>
		                            </div>
		                            <div class="form-group row">
	                        			<label class="col-sm-3 form-control-label" for="observacion"><spring:message code="observacion"/><span class="required">*</span></label>
	                                    <div class="col-sm-3">
	                                        <select name="observacion" id="observacion" class="form-control select2-single">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${catObservacion}" var="catObserv">
													<c:choose> 
														<c:when test="${catObserv.catKey eq muestra.observacion}">
															<option selected value="${catObserv.catKey}"><spring:message code="${catObserv.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${catObserv.catKey}"><spring:message code="${catObserv.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="form-group row" id="otraObservacionGroup">
	                                	<label class="col-sm-3 form-control-label" for="descOtraObservacion"><spring:message code="descOtraObservacion"/><span class="required">*</span></label>
	                                	<div class="col-sm-3">
	                                		<input type="text" name="descOtraObservacion" id="descOtraObservacion" class="form-control" value="${muestra.descOtraObservacion}"></input>
	                                	</div>
		                            </div>
		                            <div class="form-group row" id="numPinchazosGroup">
	                        			<label class="col-sm-3 form-control-label" for="numPinchazos"><spring:message code="numPinchazos"/><span class="required">*</span></label>
	                                    <div class="col-sm-3">
	                                        <select name="numPinchazos" id="numPinchazos" class="form-control select2-single">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${catPinchazos}" var="pinchazo">
													<c:choose> 
														<c:when test="${pinchazo.catKey eq muestra.numPinchazos}">
															<option selected value="${pinchazo.catKey}"><spring:message code="${pinchazo.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${pinchazo.catKey}"><spring:message code="${pinchazo.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
		                            <div class="form-group row" id="realizaPaxgeneGroup">
	                        			<label class="col-sm-3 form-control-label" for="realizaPaxgene"><spring:message code="realizaPaxgene"/><span class="required">*</span></label>
	                                    <div class="col-sm-3">
	                                        <select name="realizaPaxgene" id="realizaPaxgene" class="form-control select2-single">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${catSiNo}" var="siNo">
													<c:choose> 
														<c:when test="${siNo.catKey eq muestra.realizaPaxgene}">
															<option selected value="${siNo.catKey}"><spring:message code="${siNo.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${siNo.catKey}"><spring:message code="${siNo.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div id="horaPaxgeneGroup">
		                                <div class="form-group row">
		                                	<label class="col-sm-3 form-control-label" for="horaInicioPax"><spring:message code="horaInicioPax"/><span class="required">*</span></label>
		                                	<div class="col-sm-3">
		                                		<input type="text" name="horaInicioPax" id="horaInicioPax" class="form-control" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" 
		                                							value="${muestra.horaInicioPax}"></input>
		                                	</div>
			                            </div>
			                            <div class="form-group row">
		                                	<label class="col-sm-3 form-control-label" for="horaFinPax"><spring:message code="horaFinPax"/><span class="required">*</span></label>
		                                	<div class="col-sm-3">
		                                		<input type="text" name="horaFinPax" id="horaFinPax" class="form-control" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" 
		                                							value="${muestra.horaFinPax}"></input>
		                                	</div>
			                            </div>
		                            </div>
                                </div>
	                            <div class="form-group">
									<div class="col-md-12">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
										<a href="${fn:escapeXml(participantDataUrl)}" id="finishlink" class="btn btn-danger"><spring:message code="cancel" /></a>
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
	<spring:url value="/resources/scripts/visitas/process-samp.js" var="sintScript" />
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
	
	<spring:url value="/chf/editarcaso/saveSamp" var="saveSampUrl"/>
	<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
	<script>
	jQuery(document).ready(function() {			
		var parametros = {saveSampUrl: "${saveSampUrl}",
				processSuccess: "${processSuccess}",
				fechaInicio:"${fechaInicio}",
				fechaFin:"${fechaFin}"};
		CreateSamp.init(parametros);
	});
	</script>
	
</body>
</html>