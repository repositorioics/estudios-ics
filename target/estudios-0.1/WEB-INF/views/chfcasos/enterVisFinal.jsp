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
                            <i class="fa fa-plus"></i> <spring:message code="visitaFinal" />
                            
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
                        		<div class="row" hidden ="true">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="codigoParticipanteCaso"><spring:message code="please.enter"/> <spring:message code="codigoParticipanteCaso"/><span class="required">*</span></label>
	                                        <input type="text" readonly name="codigoParticipanteCaso" id="codigoParticipanteCaso" class="form-control" value="${participante.codigoCasoParticipante}"></input>
	                                    </div>
	                                </div>
	                            </div>
	                        	<div class="row">
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="fechaVisita"><spring:message code="please.enter"/> <spring:message code="visitDate"/><span class="required">*</span></label>
	                                        <input type="text" name="fechaVisita" id="fechaVisita" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fechaVisita}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
	                                    </div>
	                                </div>
	                            
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="horaVisita"><spring:message code="please.enter"/> <spring:message code="visitTime"/><span class="required">*</span></label>
	                                        <input type="text" name="horaVisita" id="horaVisita" class="form-control" 
	                                        		value="<fmt:formatDate value="${visFinal.fechaVisita}" pattern="HH:mm" />" pattern="([01]?[0-9]|2[0-3])(:[0-5][0-9])" required="required" placeholder="HH:mm"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-4">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="enfermo"><spring:message code="enfermo"/><span class="required">*</span></label>
	                                        <select name="enfermo" id="enfermo" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${sino}" var="sinoCat">
													<c:choose> 
														<c:when test="${sinoCat.catKey eq visFinal.enfermo}">
															<option selected value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="col-sm-4">
	                                    <div class="form-group" id="consTerrenoG">
	                                        <label class="form-control-label" for="consTerreno"><spring:message code="consTerreno"/><span class="required">*</span></label>
	                                        <select name="consTerreno" id="consTerreno" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${sino}" var="sinoCat">
													<c:choose> 
														<c:when test="${sinoCat.catKey eq visFinal.consTerreno}">
															<option selected value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="col-sm-4">
	                                    <div class="form-group" id="referidoCsG">
	                                        <label class="form-control-label" for="referidoCs"><spring:message code="referidoCs"/><span class="required">*</span></label>
	                                        <select name="referidoCs" id="referidoCs" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${sino}" var="sinoCat">
													<c:choose> 
														<c:when test="${sinoCat.catKey eq visFinal.referidoCs}">
															<option selected value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-6">
	                                    <div class="form-group" id="tratamientoG">
	                                        <label class="form-control-label" for="tratamiento"><spring:message code="tratamiento"/><span class="required">*</span></label>
	                                        <select name="tratamiento" id="tratamiento" class="form-control select2-multiple" multiple>
	                                            <c:forEach items="${tratamientos}" var="tratCat">
													<c:choose> 
														<c:when test="${fn:contains(visFinal.tratamiento, tratCat.catKey)}">
															<option selected value="${tratCat.catKey}"><spring:message code="${tratCat.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${tratCat.catKey}"><spring:message code="${tratCat.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="col-sm-6">
	                                    <div class="form-group" id="cualAntibioticoG">
	                                        <label class="form-control-label" for="cualAntibiotico"><spring:message code="cualAntibiotico"/><span class="required">*</span></label>
	                                        <input type="text" name="cualAntibiotico" id="cualAntibiotico" class="form-control" value="${visFinal.cualAntibiotico}"></input>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col-sm-4">
	                                    <div class="form-group">
	                                        <label class="form-control-label" for="sintResp"><spring:message code="sintResp"/><span class="required">*</span></label>
	                                        <select name="sintResp" id="sintResp" class="form-control">
		                                        <option selected value=""><spring:message code="select" />...</option>
	                                            <c:forEach items="${sino}" var="sinoCat">
													<c:choose> 
														<c:when test="${sinoCat.catKey eq visFinal.sintResp}">
															<option selected value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${sinoCat.catKey}"><spring:message code="${sinoCat.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="row">
                                    <div class="form-group col-sm-4" id="fiebreG">
                                        <label class="form-control-label" for="fiebre"><spring:message code="fiebre"/><span class="required">*</span></label>
                                        <select name="fiebre" id="fiebre" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sinod}" var="sinodCat">
												<c:choose> 
													<c:when test="${sinodCat.catKey eq visFinal.fiebre}">
														<option selected value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4" id="fifG">
                                        <label class="form-control-label" for="fif"><spring:message code="fi"/><span class="required">*</span></label>
                                        <input type="text" name="fif" id="fif" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fif}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                    <div class="form-group col-sm-4" id="fffG">
                                        <label class="form-control-label" for="fff"><spring:message code="ff"/><span class="required">*</span></label>
                                        <input type="text" name="fff" id="fff" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fff}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
	                            </div>
	                            <div class="row">
                                    <div class="form-group col-sm-4" id="tosG">
                                        <label class="form-control-label" for="tos"><spring:message code="tos"/><span class="required">*</span></label>
                                        <select name="tos" id="tos" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sinod}" var="sinodCat">
												<c:choose> 
													<c:when test="${sinodCat.catKey eq visFinal.tos}">
														<option selected value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4" id="fitosG">
                                        <label class="form-control-label" for="fitos"><spring:message code="fi"/><span class="required">*</span></label>
                                        <input type="text" name="fitos" id="fitos" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fitos}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                    <div class="form-group col-sm-4" id="fftosG">
                                        <label class="form-control-label" for="fftos"><spring:message code="ff"/><span class="required">*</span></label>
                                        <input type="text" name="fftos" id="fftos" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fftos}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
	                            </div>  
	                            <div class="row">
                                    <div class="form-group col-sm-4" id="dolorGargantaG">
                                        <label class="form-control-label" for="dolorGarganta"><spring:message code="dolorGarganta"/><span class="required">*</span></label>
                                        <select name="dolorGarganta" id="dolorGarganta" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sinod}" var="sinodCat">
												<c:choose> 
													<c:when test="${sinodCat.catKey eq visFinal.dolorGarganta}">
														<option selected value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4" id="figgG">
                                        <label class="form-control-label" for="figg"><spring:message code="fi"/><span class="required">*</span></label>
                                        <input type="text" name="figg" id="figg" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.figg}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                    <div class="form-group col-sm-4" id="ffggG">
                                        <label class="form-control-label" for="ffgg"><spring:message code="ff"/><span class="required">*</span></label>
                                        <input type="text" name="ffgg" id="ffgg" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.ffgg}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
	                            </div>    
	                            <div class="row">
                                    <div class="form-group col-sm-4" id="secrecionNasalG">
                                        <label class="form-control-label" for="secrecionNasal"><spring:message code="secrecionNasal"/><span class="required">*</span></label>
                                        <select name="secrecionNasal" id="secrecionNasal" class="form-control">
	                                        <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sinod}" var="sinodCat">
												<c:choose> 
													<c:when test="${sinodCat.catKey eq visFinal.secrecionNasal}">
														<option selected value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${sinodCat.catKey}"><spring:message code="${sinodCat.messageKey}" /></option>
													</c:otherwise>
												</c:choose> 
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4" id="fisnG">
                                        <label class="form-control-label" for="fisn"><spring:message code="fi"/><span class="required">*</span></label>
                                        <input type="text" name="fisn" id="fisn" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.fisn}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
                                    </div>
                                    <div class="form-group col-sm-4" id="ffsnG">
                                        <label class="form-control-label" for="ffsn"><spring:message code="ff"/><span class="required">*</span></label>
                                        <input type="text" name="ffsn" id="ffsn" class="form-control date-picker" value="<fmt:formatDate value="${visFinal.ffsn}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"></input>
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
	<spring:url value="/resources/scripts/visitas/process-visit-final.js" var="visitScript" />
	<script src="${visitScript}" type="text/javascript"></script>
	
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
	
	<spring:url value="/chf/editarcaso/saveVisitFinal" var="saveVisitFinalUrl"/>
	<c:set var="processSuccess"><spring:message code="process.success" /></c:set>
	<script>
		jQuery(document).ready(function() {			
			var parametros = {saveVisitFinalUrl: "${saveVisitFinalUrl}",
					processSuccess: "${processSuccess}",
					fechaInicio:"${fechaInicio}"};
			CreateVisit.init(parametros);
		});
		
	</script>
	
</body>
</html>