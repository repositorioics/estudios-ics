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
                            <i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="sympt" />
                            
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
	                                    <p class="form-control-static"><c:out value="${visita.codigoParticipanteCaso.participante.participante.nombreCompleto}"/>  -  <spring:message code="visit" /> <c:out value="${visita.visita}"/> 
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
                        	<form action="#" autocomplete="off" id="sint-form" class="form-horizontal">
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="codigoCasoVisita"><spring:message code="please.enter"/> <spring:message code="codigoCasoVisita"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="codigoCasoVisita" id="codigoCasoVisita" class="form-control" value="${visita.codigoCasoVisita}"></input>
                                    </div>
                                </div>
                        		<div class="form-group row" hidden="true">
                        			<label class="col-sm-3 form-control-label" for="codigoCasoSintoma"><spring:message code="please.enter"/> <spring:message code="codigoCasoSintoma"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly name="codigoCasoSintoma" id="codigoCasoSintoma" class="form-control" value="${sintoma.codigoCasoSintoma}"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="fechaSintomas"><spring:message code="please.enter"/> <spring:message code="fechaSintomas"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="fechaSintomas" id="fechaSintomas" class="form-control date-picker" value="<fmt:formatDate value="${sintoma.fechaSintomas}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="fiebre"><spring:message code="fiebre"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="fiebre" id="fiebre" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.fiebre}">
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
                                <div class="form-group row" id = "fifGroup">
                        			<label class="col-sm-3 form-control-label" for="fif"><spring:message code="fif"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="fif" id="fif" class="form-control date-picker" value="<fmt:formatDate value="${sintoma.fif}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="fiebreCuantificada"><spring:message code="fiebreCuantificada"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="fiebreCuantificada" id="fiebreCuantificada" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.fiebreCuantificada}">
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
                                <div class="form-group row" id = "fiebreCuantificadaGroup">
                        			<label class="col-sm-3 form-control-label" for="valorFiebreCuantificada"><spring:message code="valorFiebreCuantificada"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="valorFiebreCuantificada" id="valorFiebreCuantificada" class="form-control" value="${sintoma.valorFiebreCuantificada}"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="dolorCabeza"><spring:message code="dolorCabeza"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="dolorCabeza" id="dolorCabeza" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.dolorCabeza}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="dolorArticular"><spring:message code="dolorArticular"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="dolorArticular" id="dolorArticular" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.dolorArticular}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="dolorMuscular"><spring:message code="dolorMuscular"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="dolorMuscular" id="dolorMuscular" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.dolorMuscular}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="dificultadRespiratoria"><spring:message code="dificultadRespiratoria"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="dificultadRespiratoria" id="dificultadRespiratoria" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.dificultadRespiratoria}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="secrecionNasal"><spring:message code="secrecionNasal"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="secrecionNasal" id="secrecionNasal" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.secrecionNasal}">
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
                                <div class="form-group row" id = "fsnGroup">
                        			<label class="col-sm-3 form-control-label" for="fsn"><spring:message code="fsn"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="fsn" id="fsn" class="form-control date-picker" value="<fmt:formatDate value="${sintoma.fsn}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="tos"><spring:message code="tos"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="tos" id="tos" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.tos}">
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
                                <div class="form-group row" id = "ftosGroup">
                        			<label class="col-sm-3 form-control-label" for="ftos"><spring:message code="ftos"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="ftos" id="ftos" class="form-control date-picker" value="<fmt:formatDate value="${sintoma.ftos}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="pocoApetito"><spring:message code="pocoApetito"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="pocoApetito" id="pocoApetito" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.pocoApetito}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="dolorGarganta"><spring:message code="dolorGarganta"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="dolorGarganta" id="dolorGarganta" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.dolorGarganta}">
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
                                <div class="form-group row" id = "frrGroup">
                        			<label class="col-sm-3 form-control-label" for="frr"><spring:message code="frr"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <input type="text" name="frr" id="frr" class="form-control date-picker" value="<fmt:formatDate value="${sintoma.frr}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="diarrea"><spring:message code="diarrea"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="diarrea" id="diarrea" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.diarrea}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="quedoCama"><spring:message code="quedoCama"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="quedoCama" id="quedoCama" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.quedoCama}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="respiracionRuidosa"><spring:message code="respiracionRuidosa"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="respiracionRuidosa" id="respiracionRuidosa" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.respiracionRuidosa}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="oseltamivir"><spring:message code="oseltamivir"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="oseltamivir" id="oseltamivir" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.oseltamivir}">
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
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="antibiotico"><spring:message code="antibiotico"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="antibiotico" id="antibiotico" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.antibiotico}">
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
                                <div class="form-group" id = "antibioticoGroup">
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="cualAntibiotico"><spring:message code="cualAntibiotico"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <input type="text" name="cualAntibiotico" id="cualAntibiotico" class="form-control" value="${sintoma.cualAntibiotico}"></input>
                                    </div>
                                </div>
                                <div class="form-group row">
                        			<label class="col-sm-3 form-control-label" for="prescritoMedico"><spring:message code="prescritoMedico"/><span class="required">*</span></label>
                                    <div class="col-sm-3">
                                        <select name="prescritoMedico" id="prescritoMedico" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sino}" var="sinoCat">
												<c:choose> 
													<c:when test="${sinoCat.catKey eq sintoma.prescritoMedico}">
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
	<spring:url value="/resources/scripts/visitas/process-sint.js" var="sintScript" />
	<script src="${sintScript}" type="text/javascript"></script>
	
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
	
	<spring:url value="/chf/editarcaso/saveSint" var="saveSintUrl"/>
	<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
	<script>
		jQuery(document).ready(function() {			
			var parametros = {saveSintUrl: "${saveSintUrl}",
					processSuccess: "${processSuccess}",
					fechaInicio:"${fechaInicio}",
					fechaFin:"${fechaFin}"};
			CreateSint.init(parametros);
		});
		if ($('#fiebre').val() != "1") $("#fifGroup").hide();
		if ($('#fiebreCuantificada').val() != "1") $("#fiebreCuantificadaGroup").hide();		
		if ($('#tos').val() != "1") $("#ftosGroup").hide();
		if ($('#secrecionNasal').val() != "1") $("#fsnGroup").hide();
		if ($('#dolorGarganta').val() != "1") $("#frrGroup").hide();
		if ($('#antibiotico').val() != "1") $("#antibioticoGroup").hide();
	</script>
	
</body>
</html>