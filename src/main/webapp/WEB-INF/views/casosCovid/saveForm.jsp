<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 01/06/2020
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title></title>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>"><spring:message code="covid19.positives" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/covid/SaveForm/" htmlEscape="true "/>"><spring:message code="add" /> <spring:message code="positive" /></a>
            </li>
        </ol>
        <spring:url value="/covid/saveCaseCovid" var="saveUrl"/>
        <spring:url value="/covid/searchParticipant" var="searchUrl"/>
        <spring:url value="/covid/listCovid/" var="listaUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid">
            <div class="card bg-dark">
                <div class="card-header">
                    <h3 class="page-title">
                        <small><spring:message code="search" /> <spring:message code="positive" /></small>
                    </h3>
                </div>
                <div class="card-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <br/>
                                <form action="#" autocomplete="off" id="search-participant-form" class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-2" for="username"><spring:message code="participant.code" />
                                            <span class="required">*</span>
                                        </label>
                                        <div class="input-group col-md-10">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                            <input id="participantCode" name="participantCode" type="text" value="" class="form-control"/>
                                            <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                                <i class="fa fa-search" aria-hidden="true"></i>
                                                <spring:message code="search" />
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                        <small>
                            <c:choose>
                                <c:when test="${agregando}">
                                    <spring:message code="add" />
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="edit" />
                                </c:otherwise>
                            </c:choose>
                        </small>
                    </h3>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-8">
                            <form action="#" autocomplete="off" id="version-form" class="form-horizontal">
                                <input id="codigo" name="codigo" hidden="hidden" type="text" value="${caso.codigoCaso.codigoCaso}" class="form-control"/>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="estudios"><spring:message code="userstudies" />
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-list"></i>
												</span>
                                        <input id="estudios" name="estudios" type="text" readonly  class="form-control" value="${estudios}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="codigoCasa"><spring:message code="chf.house" /> (Sí pertenece a CHF)
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="codigoCasa" name="codigoCasa" type="text" readonly  class="form-control" value="${caso.codigoCaso.casa.codigoCHF}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="codigoParticipante"><spring:message code="positive" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="codigoParticipante" name="codigoParticipante" type="text" readonly  class="form-control" value="${caso.participante.codigo}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="logindate" />
										<span class="required">
											 *
										</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                               value="<fmt:formatDate value="${caso.codigoCaso.fechaIngreso}" pattern="dd/MM/yyyy" />" required="required" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-md-3 form-control-label" for="positivoPor"><spring:message code="lbl.positive.by"/><span class="required">*</span></label>
                                    <div class="col-md-9">
                                        <select name="positivoPor" id="positivoPor" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${positivoPor}" var="cat">
                                                <c:choose>
                                                    <c:when test="${caso.positivoPor eq cat.catKey}">
                                                        <option selected value="${cat.catKey}">${cat.spanish}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${cat.catKey}">${cat.spanish}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="FIS"><spring:message code="FIS" />
										<span class="required">
											 *
										</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                        <input name="fis" id="fis" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required"
                                               value="<fmt:formatDate value="${caso.fis}" pattern="dd/MM/yyyy" />"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fif"><spring:message code="fif" /> </label>
                                    <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                        <input name="fif" id="fif" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                               value="<fmt:formatDate value="${caso.fif}" pattern="dd/MM/yyyy" />"/>
                                    </div>
                                </div>

                                <div class="form-actions fluid">
                                    <div class="col-md-12">
                                        <button id="guardar" type="submit" class="btn btn-primary btn-lg btn-ladda" data-style="expand-right">
                                           <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>

                                        <a href="${fn:escapeXml(listaUrl)}" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                             <i class="fa fa-minus-circle" aria-hidden="true"></i><spring:message code="cancel" /></a>
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
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/views/covid19/processCovid.js" var="processCovidJs" />
<script src="${processCovidJs}"></script>

<script>
    jQuery(document).ready(function(){
        var parameters = {
            searchUrl : "${searchUrl}",
            saveUrl: "${saveUrl}",
            listaUrl: "${listaUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}"
        };
        handleDatePickers("${lenguaje}");
        processCasosCovid.init(parameters);
    })
</script>
</body>
</html>
